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
        console.log($.ajaxSetup.toString())
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
                console.log(JSON.stringify(data));
                alert(JSON.stringify(data));
                //$('#user-profile-raw-json').text(JSON.stringify(data, null, 2));
                //$('#user-profile-modal').modal();
            })
        });
        this.uiElements.sparqlNativeQueryButton.click(function (e) {
            var url = that.data.config.apiBaseUrl + '/sparql';
            var ontology = $('#ontologyUrl').val();
            var query = $('#sparqlNativeQuery').val();
            /*
            $.ajaxSetup({
                'beforeSend': function (xhr) {
                    xhr.setRequestHeader('ontology', ontology.toString());
                    xhr.setRequestHeader('query', query.toString())
                }
            });
            */
            $.get(url, function (data, status) {
                console.log(data);
                alert(JSON.stringify(data, null, 2));
            })
        });
        this.uiElements.feedbackButton.click(function (e) {
            const name = $('#feedbackInput').val();
            var url = that.data.config.apiBaseUrl + '/feedback/java' + '/'+name.toString();
            console.log(url);
            $.get(url, function (data, status) {
                console.log(data);
                console.log(status);
            })
        })
    }
};
/*
PREFIX owl: <http://www.w3.org/2002/07/owl#>
                            PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                            PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>
                            SELECT ?comp WHERE {
                            ?comp rdf:type owl:Class .
                            }
                            ORDER BY ?label
                        </textarea>
 */

/*
    var SparqlParser = require('sparqljs').Parser;
    var parser = new SparqlParser();
    var parsedQuery = parser.parse(txt);

// Regenerate a SPARQL query from a JSON object
    var SparqlGenerator = require('sparqljs').Generator;
    var generator = new SparqlGenerator();
    query.variables = ['?mickey'];
    var generatedQuery = generator.stringify(query);
    console.log(generatedQuery);
 */
/*
    this.uiElements.s3InfoButton.click(function (e) {
      var url = that.data.config.apiBaseUrl + '/s3info';
      $.get(url, function (data, status) {
        console.log(data);
          alert(JSON.stringify(data, null, 2));
      })
    });
    this.uiElements.readS3UpdateDynamo.click(function (e) {
        var url = that.data.config.apiBaseUrl + '/readS3UpdateDynamo';
        $.get(url, function (data, status) {
            alert(JSON.stringify(data));
        })
    });
*/