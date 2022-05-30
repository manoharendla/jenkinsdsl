job('ci-1') {
    description 'Build and test the app.'
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
        gradle 'test'
    }
    publishers {
        archiveJunit 'build/test-results/**/*.xml'
    }
}
