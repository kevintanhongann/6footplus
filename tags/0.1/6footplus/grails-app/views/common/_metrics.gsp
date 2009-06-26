<%@ page import="grails.util.Environment" %>
<g:if test="${Environment.current == Environment.PRODUCTION}">
    <script type="text/javascript">var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www."); document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));</script>
    <script type="text/javascript">var pageTracker = _gat._getTracker("UA-590454-1"); pageTracker._trackPageview();</script>
</g:if> 