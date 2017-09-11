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
        querySelectSubject: null,
        querySelectPredicate: null,
        querySelectObject: null,
        templateButton01: null,
        templateButton02: null,
        templateButton03: null,
        sparqlNativeQueryButton: null,
        feedbackButton: null,
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
        this.uiElements.querySelectSubject = $('#querySelectSubject');
        this.uiElements.querySelectPredicate = $('#querySelectPredicate');
        this.uiElements.querySelectObject = $('#querySelectObject');
        this.uiElements.templateButton01 = $('#templateButton01');
        this.uiElements.templateButton02 = $('#templateButton02');
        this.uiElements.templateButton03 = $('#templateButton03');


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
            let url = that.data.config.apiBaseUrl + '/userprofile';
            $.get(url, function (data, status) {
                alert(JSON.stringify(data))
            })
        });
        this.uiElements.sparqlNativeQueryButton.click(function (e) {
            let url = that.data.config.apiBaseUrl + '/sparql';
            let reqBody = {};
            let query = $('#sparqlNativeQuery').val();
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
                    let table = $('#table');

                    $(function () {
                        const parsedData = JSON.parse(data);
                        let columns = buildTableHeaders(parsedData['body'][0]);

                        table.bootstrapTable('destroy');
                        table.bootstrapTable({
                            columns: columns,
                            data: parsedData['body']
                        });
                    });
                },
                error: function (jqXhr, textStatus, errorThrown) {
                    console.log(errorThrown);
                }
            });
        });
        this.uiElements.selectEntityMultipleButton.click(function (e) {
            let name = $('#selectEntityMultiple').val();
            let url = that.data.config.apiBaseUrl + '/page' + '/' + name.toString();
            console.log(url);
            $.get(url, function (data, status) {
                let table = $('#table');
                let columns = buildTableHeaders(data['body'][0]);
                $(function () {

                    table.bootstrapTable('destroy');
                    //table.append(thFrom(data['body'][0]));

                    table.bootstrapTable({
                        columns: columns,
                        data: data['body']
                    });
                });
            })

        });
        this.uiElements.feedbackButton.click(function (e) {
            let table = $('#table');
            let name = $('#feedbackInput').val();
            let url = that.data.config.apiBaseUrl + '/page' + '/' + name.toString();
            console.log(url);
            $.get(url, function (data, status) {
                let columns = [];
                Object.keys(data['body'][0]).forEach(function (t){
                    columns.push({
                        field: t,
                        title: t,
                        sortable: true
                    });
                });

                $(function () {
                    table.bootstrapTable('destroy');
                    //table.append(thFrom(data['body'][0]));
                    table.bootstrapTable({
                        columns: columns,
                        data: data['body']
                    });
                });
            })
        });

        this.uiElements.querySelectSubject.click(function (e) {
            $('#templateSpanText01').text($('#querySelectSubject').val())
        });
        this.uiElements.querySelectPredicate.click(function (e) {
            $('#templateSpanText02').text($('#querySelectPredicate').val())
        });
        this.uiElements.querySelectObject.click(function (e) {
            $('#templateSpanText03').text($('#querySelectObject').val())
        });
        this.uiElements.templateButton01.click(function (e) {
            executeQuery($('#template01').text());
        });


        function executeQuery(aQuery) {

            let query = "PREFIX owl: <http://www.w3.org/2002/07/owl#>" +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
                "PREFIX koma: <https://s3-us-west-2.amazonaws.com/ontology.thb.de/koma-complex.owl#>";

            query += "\n";
            query += aQuery;

            //query += $('#querySelectSubject'); // the type of the first variable
            let reqBody = {};
            reqBody.bucketKey = "koma-complex.owl";
            reqBody.query = query;
            const url = that.data.config.apiBaseUrl + '/sparql';
            $.ajax({
                url: url,
                dataType: 'json',
                type: 'post',
                contentType: 'application/json',
                data: JSON.stringify(reqBody),
                processData: false,
                success: function (data, textStatus, jQxhr) {
                    let table = $('#table');

                    $(function () {
                        const parsedData = JSON.parse(data);
                        let columns = buildTableHeaders(parsedData['body'][0]);

                        table.bootstrapTable('destroy');
                        table.bootstrapTable({
                            columns: columns,
                            data: parsedData['body']
                        });
                    });
                },
                error: function (jqXhr, textStatus, errorThrown) {
                    console.log(errorThrown);
                }
            });



            console.log(query)
        };

        /**
         *
         * @param cleanJson
         * @returns {Array}
         */
        function buildTableHeaders(cleanJson) {
            let columns = [];
            Object.keys(cleanJson).forEach(function (t){
                columns.push({
                    field: t,
                    title: t,
                    sortable: true
                });
            });
            return columns
        }
    }
};
