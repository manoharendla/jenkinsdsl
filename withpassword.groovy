job('ci-1') {
    description 'Build and test the app.'
     parameters {
        nonStoredPasswordParam('myParameterName', 'my description')
    }
    configure { project -> 
       project / 'buildWrappers' / 'EnvInjectPasswordWrapper' {
         injectGlobalPasswords ('true')
         maskPasswordParameters ('true')           
            
        }
    }
    scm {
        git {
            remote {
                github('account/repo', 'https')
                credentials('github-ci-key')
                branch('*/main')
            }
        }
    }
    steps {
        shell """
echo 'hi'
echo 'hello'
"""
    }
    publishers {
        archiveJunit 'build/test-results/**/*.xml'
    }
}
