# CricketVerse 🏏
The ultimate cricket experience:
- Live Scores via API
- Animated Player Profiles
- Cricket-Themed Minigames
- Quiz, News, Stats & More

## Local Demo

For offline testing, the app reads scores from `sample_scores.json` in the `assets` directory. This lets you see sample match data without a network connection.

## Development Setup

1. Ensure the Android SDK is installed and `sdk.dir` in `local.properties` points to it.
2. Build the debug APK with `gradle assembleDebug`.
3. Run unit tests with `gradle test` (requires SDK access).

The `assets` folder also includes `sample_quiz.json` with example quiz data used by the quiz feature when no network is available.
