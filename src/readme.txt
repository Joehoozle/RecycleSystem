EcoRe Recycling System
By Patterson Jaffurs and Dante Dalla Gasperina
Made using Java version 1.8
Using IntelliJ IDEA 2016.3.4
Additional files are included in the .zip to ensure that the .jar file works.


Design Overview

Package structure was done in such a way that all of the front-end classes are in one file and the back-end in another,
considering helper classes like USMoney as back-end. To describe the general structure, TabbedDisplay is the main class,
creating an instance of RMOS as one window and two instances (for demo purposes) of RMOS in a tabbed window. From the
diagrams, RMOS holds RCMFunctions, so they can in turn interact with their respective RCM. Updates go back and forth,
changing what the user sees as needed. The UI is designed to be easy to use and fluid.


Implementation Notes

1) Logging in with the login pop-up is required to access the RMOS. The username is “admin” and the password is “test”
2) Both the RMOS and RCM offer an “update button” if the system does not seem to be updating after a change. The RMOS’s
update button is in the RCM control panel on the left, where the RCM’s update button is on the top of the RCM.
3) The “add RCM” functionality of the RMOS is not functional at this point, but we left it in the RMOS as it could be an
important functionality if we wanted to add to this project in the future.
3) The statistic panel only has two statistics at this point, but we wanted to add more in the future. It would be easy
to add extras as we would just append them to the combobox.
4) Each RCM can be turned off with the on and off button in the RMOS. If you turn an RCM off in the RMOS, you must
update the chosen RCM with the “update” before the effect takes place. The off button does not allow RCM access from the
RMOS or the RCM itself.
