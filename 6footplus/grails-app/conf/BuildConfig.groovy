coverage {
    exclusions = [
        '**/com/burtbeckwith/**',
        '**/com/studentsonly/**',
        '**/feedsplugin/**',
        '**/grails/util/**',
        '**/org/grails/**',
        '**/org/jsecurity/grails/**',
        '**/winterwell/jtwitter/**',
        '**/AuthController*',
        '**/FormHelperTagLib*',
        '**/BuildConfig*',
        '**/FeedsTagLib*',
        '**/JQueryTagLib*',
        '**/JobManagerService*',
        '**/Jsec*',
        '**/Searchable*',
        '**/SecurityFilters*',
        '**/TwitterFilter*',
        '**/*QuartzConfig*']
}
grails.war.resources = { stagingDir ->
    delete(file:"${stagingDir}/WEB-INF/lib/mysql-connector-java-5.1.7-bin.jar")
    delete(file:"${stagingDir}/WEB-INF/lib/hsqldb-1.8.0.10.jar")
}
