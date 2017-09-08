var userController = {
    data: {
        auth0Lock: null,
        config: null
    },
    uiElements: {
        loginButton: null,
        logoutButton: null,
        profileButton: null,
        profileNameLabel: null,
        profileImage: null,
        selectEntityMultipleButton: null,
        sparqlNativeQueryButton: null,
        feedbackButton: null
    },
    init: function (config) {
        var that = this;

        this.uiElements.loginButton = $('#auth0-login');
        this.uiElements.logoutButton = $('#auth0-logout');
        this.uiElements.profileButton = $('#user-profile');
        this.uiElements.profileNameLabel = $('#profilename');
        this.uiElements.profileImage = $('#profilepicture');
        this.uiElements.selectEntityMultipleButton = $('#selectEntityMultipleButton');
        this.uiElements.sparqlNativeQueryButton = $('#sparqlNativeQueryButton');
        this.uiElements.feedbackButton = $('#feedbackButton');

        this.data.config = config;
        this.data.auth0Lock = new Auth0Lock(config.auth0.clientId, config.auth0.domain);

        var idToken = localStorage.getItem('userToken');

        if (idToken) {
            this.configureAuthenticatedRequests();
            this.data.auth0Lock.getProfile(idToken, function (err, profile) {
                if (err) {
                    return alert('There was an error getting the profile: ' + err.message);
                }
                that.showUserAuthenticationDetails(profile);
            });
        }

        this.wireEvents();
    },
    configureAuthenticatedRequests: function () {
        $.ajaxSetup({
            'beforeSend': function (xhr) {
                xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('userToken'));
            }
        });
    },
    showUserAuthenticationDetails: function (profile) {
        var showAuthenticationElements = !!profile;

        if (showAuthenticationElements) {
            this.uiElements.profileNameLabel.text(profile.nickname);
            this.uiElements.profileImage.attr('src', profile.picture);
        }

        this.uiElements.loginButton.toggle(!showAuthenticationElements);
        this.uiElements.logoutButton.toggle(showAuthenticationElements);
        this.uiElements.profileButton.toggle(showAuthenticationElements);
    },
    wireEvents: function () {
        var that = this;

        this.uiElements.loginButton.click(function (e) {
            var params = {
                authParams: {
                    scope: 'openid email user_metadata picture'
                }
            };

            that.data.auth0Lock.show(params, function (err, profile, token) {
                if (err) {
                    // Error callback
                    alert('There was an error');
                } else {
                    // Save the JWT token.
                    localStorage.setItem('userToken', token);
                    that.configureAuthenticatedRequests();
                    that.showUserAuthenticationDetails(profile);
                }
            });
        });

        this.uiElements.logoutButton.click(function (e) {
            localStorage.removeItem('userToken');

            that.uiElements.logoutButton.hide();
            that.uiElements.profileButton.hide();
            that.uiElements.loginButton.show();
        });
        this.uiElements.profileButton.click(function (e) {
            var url = that.data.config.apiBaseUrl + '/userprofile';
            $.get(url, function (data, status) {
                alert(JSON.stringify(data))
            })
        });
        this.uiElements.sparqlNativeQueryButton.click(function (e) {
            var dynTable = "<thead>\n" +"<tr>\n";
            var table = $('#table');
            var url = that.data.config.apiBaseUrl + '/sparql';
            var reqBody = {};
            var query = $('#sparqlNativeQuery').val();
            reqBody.bucketKey = "koma-complex.owl";
            reqBody.query = query;
            $.ajax({
                url: url,
                dataType: 'json',
                type: 'post',
                contentType: 'application/json',
                data: JSON.stringify(reqBody),
                processData: false,
                success: function (data, textStatus, jQxhr) {
                    $(function () {
                        var parsedData = JSON.parse(data);
                        console.log(Object.keys(parsedData['body'][0]));
                        Object.keys(parsedData['body'][0]).forEach(function (t) {
                            dynTable += "<th data-field=\"" +t+ "\"> t </th>\n"
                        });
                        dynTable += "</tr>\n" + "</thead>\n";

                        table.bootstrapTable('destroy');
                        table.append(dynTable);
                        table.bootstrapTable({
                            data: parsedData['body']
                        });
                    });
                    console.log(JSON.stringify(data))
                },
                error: function (jqXhr, textStatus, errorThrown) {
                    console.log(errorThrown);
                }
            });
        });
        this.uiElements.selectEntityMultipleButton.click(function (e) {
            var table = $('#table');
            var name = $('#selectEntityMultiple').val();
            var url = that.data.config.apiBaseUrl + '/page' + '/' + name.toString();
            console.log(url);
            $.get(url, function (data, status) {
                $(function () {
                    table.bootstrapTable('destroy');
                    table.bootstrapTable({
                        data: data['body']
                    });
                });
            })

        });
        this.uiElements.feedbackButton.click(function (e) {
            var table = $('#table');
            var name = $('#feedbackInput').val();
            var url = that.data.config.apiBaseUrl + '/page' + '/' + name.toString();
            console.log(url);
            $.get(url, function (data, status) {
                $(function () {
                    table.bootstrapTable('destroy');
                    table.bootstrapTable({
                        data: data['body']
                    });
                });
            })
        });
    }
};
