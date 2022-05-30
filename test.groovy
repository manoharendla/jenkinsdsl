job('ci-1') {
    description 'Build and test the app.'
    configure { project -> 
       project / 'buildWrappers' / 'EnvInjectPasswordWrapper' {
         injectGlobalPasswords ('true')
         maskPasswordParameters ('true')           
            
        }
    }
    scm {
        github 'sheehan/job-dsl-playground'
    }
    steps {
        gradle 'test'
    }
    publishers {
        archiveJunit 'build/test-results/**/*.xml'
    }
}
