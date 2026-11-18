# PocketPlant

Android plant tracker app built for the **CIS4034-N Mobile App Development** ICA at Teesside University.

- **Student:** Suma Bathula (S3633826)
- **Tutor:** Julien Cordry
- **Module:** CIS4034-N Mobile App Development
- **Status:** Sprint 1 in progress (see Sprint status below)

---

## What the app will do (full scope across all sprints)

PocketPlant lets a beginner plant owner keep a list of the houseplants they own on their own phone. The user will be able to:

- Save plants by common name and personal nickname.
- Read care details (watering, sunlight, difficulty) pulled from the free Perenual plant catalogue.
- See a live reading from the phone's ambient-light sensor to check if the current room is bright enough for the plant.

Everything stays on the device. No sign-up, no account, no cloud copy of the user's plants.

---

## Sprint status

### Sprint 1 (current): splash and placeholder home screen

- **US-01:** The user sees a welcome splash screen when the app opens and lands on an empty "My Plants" home screen.
- **Acceptance criteria:**
  - Splash appears within 1 second of launch.
  - Splash shows the app name "PocketPlant" and a plant leaf icon.
  - After 1.5 seconds the app transitions to the My Plants screen with the title "My Plants" and an empty-state message.
- **Points:** 2

### Sprint 2 (planned): local plant storage

- US-04: see the list of saved plants
- US-05: add a plant by common name and nickname
- US-06: swipe a plant off the list with Undo
- US-07: all storage is local; no network calls

### Sprint 3+ (planned)

Perenual plant catalogue, ambient-light sensor, settings, tests, security audit.

---

## How to run this on an Android device or emulator

Requirements:

- Android Studio Ladybug or newer (AGP 8.13.2, Kotlin 2.0.21, JDK 17).
- A device or emulator running Android 10 (API 29) or newer.

Steps:

1. Open this folder (`Source/PocketPlant`) in Android Studio as a project.
2. Let Gradle sync. First sync may take a few minutes.
3. Press the green Run button, pick a device, wait for the APK to install.
4. The app shows the splash screen for 1.5 seconds, then the empty My Plants screen.

Or from the command line:

```bash
./gradlew.bat assembleDebug
```

The debug APK is written to `app/build/outputs/apk/debug/app-debug.apk`.

---

## Tech stack (locked for this sprint)

- Language: Kotlin 2.0.21
- UI: Jetpack Compose (BOM 2024.11)
- Material 3
- Navigation: androidx.navigation.compose 2.8.4
- Minimum SDK: 29 (Android 10)
- Target SDK: 36

No Room, no network, no sensors yet. Those arrive in later sprints on their own feature branches.

---

## Commit history on this sprint (newest first)

Sprint 1 is built up in small commits across days so progression is visible:

- `feat(nav): placeholder My Plants screen with splash to home navigation`
- `feat(splash): welcome splash screen with leaf logo and tagline`
- `feat(theme): plant-green Material 3 colour scheme and typography`
- `chore: Android project skeleton (Gradle, Compose, Material 3)`
- `chore: initial repo with .gitignore and README`

Feature work lives on a branch named `feature/us-01-splash`. When the user story is complete, the branch is merged into `main` via a Pull Request so the review is visible on GitHub.

---

## AI-use declaration (Teesside requirement)

Per Teesside University's AI policy and the CRAFT model (Comprehend, Relevance, Analyse, Forward Thinking, Think Critically), AI assistance has been used while building this app. The use is declared here in full.

**What AI was used for:**

- Explaining Android, Kotlin and Jetpack Compose concepts.
- Suggesting file structure and commit granularity.
- Generating draft code and build scripts for the student to read, understand and commit.
- Reviewing code for simplicity and errors.
- Drafting this README.

**What AI was NOT used for:**

- Creating a GitHub account.
- Authenticating as the student.
- Making architecture decisions without the student's agreement.

**Tool used:** Claude Code (Anthropic), Opus 4.7 model, 2026-11-18.

**Student declaration:** I, Suma Bathula (S3633826), confirm that I understand every line of code in this repository, I can explain each file's purpose and behaviour in my own words, and I am able to defend this work in a viva. AI has been used as a learning and review aid, not as a replacement for my own understanding. A full AI-use declaration will be submitted on the SHLS Declaration of Use of AI form at submission.

---

## Backup of earlier work

An earlier version of this project was pushed as one large commit to an older repository. Based on tutor feedback ("no evidence of progression, big dump of code"), the old repository was deleted and this new one was started fresh with a smaller Sprint 1 scope and small commits spread across days. The original code is retained locally at `Source_old_sprint1_dump/` for reference only; it is not part of this repository.
