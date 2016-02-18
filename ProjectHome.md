<h2>Introduction</h2>

This project is a grails and groovy project, and in essence the source code for my personal home page and blog http://www.6footplus.com. Feel free to grab the code and use it for yourself!

<h2>Source code</h2>

More details <a href='http://code.google.com/p/6footplus/source/checkout'>here</a>.

<h2>Build and run the code</h2>

After you have a local copy of the source code, install the following :

  * Groovy http://groovy.codehaus.org/Installing+Groovy
  * Grails http://grails.org/Installation

Once you've done that, you'll be able to run the application from the local 6footplus svn directory created in the previous section.

Run the application as follows:

  * grails run-app

That's it! Now you are ready to browse the application at http://localhost:8080

<h2>Deploy</h2>

From the featured download list, you could also deploy the war on any given application server. Alternatively, you could also build a war on your own:

  * grails war

By default it will create a production environment web archive. If you do not have mysql (required by production environment), create a development environment web archive :

  * grails dev war

I recommend renaming the downloaded or locally built archive to ROOT.war. That way the deployed application is reachable on the root context e.g. http://localhost:8080/