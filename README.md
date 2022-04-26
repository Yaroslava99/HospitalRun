# HospitalRun
HospitalRun test automation task

You can run tests in the IDE OR using CMD
GO to the test directory and run:
- "mvn test" - to run all cases in the Chrome (default browser)
- "mvn -Dbrowser=firefox" to run all cases in the Firefox ("chrome"/"edge" are also supported)
- "mvn -Dtest=TestLogin" to run cases written for Login page
- "mvn -Dtest=TestMedication" to run cases written for Medication request
Note: Medication request test may take a long time as Patient drop-down auto-fill suggestions take time to appear.
