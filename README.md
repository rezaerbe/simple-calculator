# [Continuous Integration for Android](https://www.raywenderlich.com/10562143-continuous-integration-for-android)

*CI*, short for *continuous integration*, is a development practice in which each member of a team frequently merges their codes into the main repository branch. Each integration triggers an automated build and test workflow, allowing the team to detect and fix problems as early as possible.

In this tutorial, you’ll learn how to implement continuous integration for Android in an app called *Simple Calculator*.

In the process, you’ll learn to:

- Implement CI in your workflow.
- Use GitHub Actions.
- Integrate testing frameworks to compliment the CI workflow.

![How Simple Calculator works](https://koenig-media.raywenderlich.com/uploads/2020/05/main-7.gif)

Now, take a look at the project. It contains the following files:

[![List of files in the project structure](https://koenig-media.raywenderlich.com/uploads/2020/05/Project-Structure-2-383x320.png)](https://koenig-media.raywenderlich.com/uploads/2020/05/Project-Structure-2.png)

## Understanding Continuous Integration

Since the master branch has to be stable at any given time, no developer should push their commits directly to that branch. When working on a new feature, you must *create a new branch* from the master then work on that branch.

When you’re done, you pull changes from the main branch into yours, resolve any merge conflicts then push that branch to the project repository. Multiple developers working on the same repository should all follow that same pattern.

To keep your branch updated, you need to pull changes from the main branch frequently. This also saves you from huge merge conflicts.

When you push your changes to your repository, you trigger the CI workflow that runs the test cases. You can only merge your commit to the master after the code passes the tests.

If the tests fail, you have to hunt for the errors using the CI logs, fix them and repeat those steps. This ensures that only tested and working code gets to the master branch.

![Continuous integration workflow](https://koenig-media.raywenderlich.com/uploads/2020/05/Untitled-2020-04-28-1956-650x213.png)

## GitHub Actions

GitHub recently started providing a workflow automation feature named *GitHub Actions*. You’ll find it under the *Actions* tab of your repository.

In GitHub Actions, you specify these CLI commands using a [YAML](https://en.wikipedia.org/wiki/YAML) file. YAML is a human-friendly data serialization language like JSON, but cleaner, more readable and more expressive.

Here’s an example of a GitHub Actions workflow configuration file:

```yaml
# 1
name: Simple Workflow Example
# 2
on: [push]

# 3
jobs:
  build:
    # 4
    name: Greet
    # 5
    runs-on: ubuntu-latest
    # 6
    steps:
      - name: Hello world step
        run: echo Hello, World!

  time:
    name: Print date
    # 7
    needs: 
     - build
    runs-on: ubuntu-latest
    steps:
     - run: echo "It is $(date)"
```

### Running Unit Tests and Instrumentation Tests

For Android development, you’ll need to set up a JDK in the runner, then check out your source code in the runner’s file system. As for Gradle, Android Studio projects, by default, has a Gradle wrapper shell script *gradlew* and a Windows batch script *gradlew.bat* – which can be invoked using `./gradlew` and `./gradlew.bat` respectively.

Next, you’ll see how to design the workflows for your project.

This is the *build-and-test* job as shown below:

```yaml
jobs:
  build-and-test:
    name: Build and run tests
    # runs-on: ubuntu-latest
    runs-on: macos-latest # Switched to macOS
    steps:
        ...
      - name: Run unit tests
        run: ./gradlew testDebugUnitTest

      - name: Run instrumentation tests
        uses: reactivecircus/android-emulator-runner@v2
        with:
          api-level: 29
          arch: x86
          profile: Nexus 6
          avd-name: test
          emulator-options: -no-window -gpu swiftshader_indirect -no-snapshot -noaudio -no-boot-anim -camera-back none
          disable-animations: true
          script: ./gradlew connectedCheck
```

### Generating the APK File

After the build succeeds, you’ll generate the *APK* file and upload it as an artifact. Add this line right after the *build-and-test* job:

```yaml
generate-apk:
  name: Generate apk
  runs-on: ubuntu-latest
  needs:
    - build-and-test
  steps:
    - uses: actions/checkout@v1

    - name: Setup JDK 1.8
      uses: actions/setup-java@v1
      with:
        java-version: 1.8

    - name: Generate apk
      run: ./gradlew assembleDebug

    - name: Upload APK
      uses: actions/upload-artifact@v1
      with:
        name: build-output
        path: app/build/outputs/apk/debug/app-debug.apk
```