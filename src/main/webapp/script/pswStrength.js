jQuery(document).ready(function () {
    "use strict";
    var options = {
        minChar: 8,
        bootstrap3: false,
        errorMessages: {
            password_too_short: "<font color='red'>The Password is too short</font>",
            same_as_username: "Your password cannot be the same as your username"
        },
        scores: [17, 26, 40, 50],
        verdicts: ["Weak", "Normal", "Medium", "Strong", "Very Strong"],
        showVerdicts: true,
        showVerdictsInitially: false,
        raisePower: 1.4,
        usernameField: "#username",
    };
    $(':password').pwstrength(options);
});