<div id="footer">
    <div id="footer-left">
        <div style="background: #999966"><img alt="" src="${createLinkTo(dir: 'images', file: 'surfertop_3.gif')}" border="0" /></div>
        <p class="textlight" style="padding: 7px;">herzlich willkommen! i&#39;m a german speaking south african currently living and working in munich, germany. i&#39;m a java, grails, groovy software developer and one might notice my site being a bit of a geek&#39;s sandpit. feel free to have a gander!</p>
        <p class="textlight" style="padding: 7px;">the 6footplus fella, <br/>andreas nerlich</p>
        <div style="padding-left: 7px; margin-bottom: 5px"><a title="My Friendfeed.com" href="http://friendfeed.com/6footplus"><img src="/images/friendfeed_32.png" width="22" border="0"/></a>&nbsp;&nbsp;&nbsp;<a href="/feed/rss"><img title="My Feed" src="/images/feed_32.png" width="22" border="0"/></a>&nbsp;&nbsp;&nbsp;<a href="http://www.linkedin.com/in/andreasnerlich" title="My LinkedIn.com"><img src="/images/linkedin_32.png" width="22" border="0"/></a>&nbsp;&nbsp;&nbsp;<a href="http://twitter.com/6footplus" title="My Twitter.com"><img src="/images/twitter_32.png" border="0" width="22"/></a>&nbsp;&nbsp;&nbsp;<a href="http://vimeo.com/sixfootplus/videos" title="My Vimeo.com"><img src="/images/vimeo_32.png" width="22" border="0"/></a>&nbsp;&nbsp;&nbsp;<a href="http://www.flickr.com/photos/6footplus/" title="My Flickr.com"><img src="/images/flickr_32.png" border="0" width="22"/></a></div>
    </div>
    <div id="footer-right">    
        <div id="footer-right-top">
            <twitter:userLink class="title" name="6footplus" text="my recent tweets"/>
            <g:include controller="twitter" action="list" params="[maxTweets:3]"/>
        </div>
        <div id="footer-right-middle">
            <div id="footer-right-middle-left">
                <a href="/home/show/206" class="title" title="View more of my recent videos">my recent videos</a>
                <g:include controller="rssReader" action="show" params="[producer:'vimeo']"/>
                <p id="padding-left-20">• <a href="/home/show/206" title="View more of my recent videos">more videos</a></p>
            </div>
            <div id="footer-right-middle-center">
                <a href="http://photos.6footplus.com/recent.php" class="title" title="View more of my recent photos">my recent photos</a>
                <g:include controller="rssReader" action="show" params="[producer:'flickr']"/>
                <p id="padding-left-20">• <a href="http://photos.6footplus.com/recent.php" title="View more of my recent photos">more photos</a></p>
            </div>
            <div id="footer-right-middle-right">
                <a href="http://twitpic.com/photos/6footplus" class="title" title="View more of my recent twitter status messages on twitter.com">my recent twitpics</a>
                <g:include controller="rssReader" action="show" params="[producer:'twitpic']"/>
                <p id="padding-left-20">• <a href="http://twitpic.com/photos/6footplus" title="View more of my recent twitter status messages on twitter.com">more twitpics</a></p>
            </div>
            <div style="clear:both"></div>
        </div>
        <div id="footer-right-bottom">
            <div id="footer-right-middle-left">
                <div class="h1" style="padding-bottom: 10px">my details</div>
                <div style="padding-left: 20px; float:left; width:150px">Andreas Nerlich<br />Web Developer<br />Munich, Germany<br /><a href="/contact">contact me</a><br /></div>
            </div>
            <div id="footer-right-middle-center">
                <div style="padding-bottom: 10px"><a href="${createLink(controller:'home',action:'index',params:[max:5])}" class="title" title="View the last 5 articles on a single page">my recent articles</a></div>
                <g:include controller="home" action="recent" params="[maxRecent:4]"/>
            </div>
            <div id="footer-right-middle-right">
                <div class="h1" style="padding-bottom: 10px">more stuff</div>
                <span id="padding-left-20">• <a href="http://photos.6footplus.com/recent.php" title="View my recent photos">my photos</a></span><br/>
                <span id="padding-left-20">• <a href="http://www.flickr.com/photos/6footplus/map/" title="Map representation of where some of my photos were taken">my photo locations</a></span><br/>
                <span id="padding-left-20">• <a href="/pics/" title="View my old photos">my older photos</a></span><br/>
                <span id="padding-left-20">• <a href="/searchable" title="Search through all of my blog articles">search</a>&nbsp;<img src="/images/search.png" height="12"/></span>
            </div>
            <div style="clear:both"></div>
        </div>
    </div>
</div>
<div style="clear:both; margin-top: 10px; margin-bottom: 10px; border-bottom: 1px solid #666633;">&nbsp;</div>
<p class="date" style="text-align: right">created with groovy & grails | <a class="brown_s" href="http://code.google.com/p/6footplus/">source code</a> | <a class="brown_s" href="http://birthofnations.kicks-ass.org:8888/hudson/job/6footplus%20Blog/">continuous integration</a></p>