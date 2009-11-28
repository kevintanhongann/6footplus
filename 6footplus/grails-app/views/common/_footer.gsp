<div id="footer">
    <div id="footer-left">
        <div style="background: #999966"><img alt="" src="${createLinkTo(dir: 'images', file: 'surfertop_3.gif')}" border="0" /></div>
        <p class="textlight" style="padding: 7px;">herzlich willkommen! i&#39;m a german speaking south african currently living and working in munich, germany. i&#39;m a java, grails, groovy software developer and one might notice my site being a bit of a geek&#39;s sandpit. feel free to have a gander!</p>
        <p class="textlight" style="padding: 7px;">andreas nerlich<br />software developer<br />munich, germany<br /><a class="brown" href="/sms"><u>sms</u></a> or <a class="brown" href="/contact"><u>contact</u></a> me</p>
        <div style="padding-left: 7px; margin-bottom: 5px"><a href="http://friendfeed.com/6footplus"><tooltip:tip value="my friendfeed.com"><img src="/images/friendfeed_32.png" width="22" border="0"/></tooltip:tip></a>&nbsp;&nbsp;&nbsp;<a href="/feed/rss"><tooltip:tip value="my blog rss feed"><img src="/images/feed_32.png" width="22" border="0"/></tooltip:tip></a>&nbsp;&nbsp;&nbsp;<a href="http://www.linkedin.com/in/andreasnerlich"><tooltip:tip value="my profile on linkedin.com"><img src="/images/linkedin_32.png" width="22" border="0"/></tooltip:tip></a>&nbsp;&nbsp;&nbsp;<a href="http://twitter.com/6footplus"><tooltip:tip value="my twitter.com messages"><img src="/images/twitter_32.png" border="0" width="22"/></tooltip:tip></a>&nbsp;&nbsp;&nbsp;<a href="http://vimeo.com/sixfootplus/videos"><tooltip:tip value="my videos on vimeo.com"><img src="/images/vimeo_32.png" width="22" border="0"/></tooltip:tip></a>&nbsp;&nbsp;&nbsp;<a href="http://www.flickr.com/photos/6footplus/"><tooltip:tip value="my photos on flickr.com"><img src="/images/flickr_32.png" border="0" width="22"/></tooltip:tip></a></div>
    </div>
    <div id="footer-right">    
        <div id="footer-right-top">
            <twitter:userLink class="title" name="6footplus" text="my recent tweets"/>
            <g:include controller="twitter" action="list" params="[maxTweets:3]"/>
        </div>
        <div id="footer-right-middle">
            <div id="footer-right-middle-left">
                <tooltip:tip value="View a list of recently published videos">
                    <a href="/home/show/206" class="title">my recent videos</a>
                </tooltip:tip>
                <g:include controller="rssReader" action="show" params="[producer:'vimeo']"/>
                <p id="padding-left-20">• <a href="/home/show/206">more videos</a></p>
            </div>
            <div id="footer-right-middle-center">
                <tooltip:tip value="View all recently published photos">
                    <a href="http://photos.6footplus.com/recent.php" class="title">my recent photos</a>
                </tooltip:tip>
                <g:include controller="rssReader" action="show" params="[producer:'flickr']"/>
                <p id="padding-left-20">• <a href="http://photos.6footplus.com/recent.php">more photos</a></p>
            </div>
            <div id="footer-right-middle-right">
                <tooltip:tip value="View more of my recent twitter images on twitpic.com">
                    <a href="http://twitpic.com/photos/6footplus" class="title">my recent twitpics</a>
                </tooltip:tip>
                <g:include controller="rssReader" action="show" params="[producer:'twitpic']"/>
                <p id="padding-left-20">• <a href="http://twitpic.com/photos/6footplus">more twitpics</a></p>
            </div>
            <div style="clear:both"></div>
        </div>
        <div id="footer-right-bottom">
            <div id="footer-right-middle-right">
                <div class="h1" style="padding-bottom: 10px">more stuff</div>
                <span id="padding-left-20">• <a href="http://photos.6footplus.com/recent.php">my photos</a></span><br/>
                <span id="padding-left-20">• <a href="http://www.flickr.com/photos/6footplus/map/">my photo locations</a></span><br/>
                <span id="padding-left-20">• <a href="/pics/">my older photos</a></span><br/>
                <span id="padding-left-20">• <a href="/searchable">search</a>&nbsp;<img src="/images/search.png" height="12"/></span>
            </div>
            <div id="footer-right-middle-two-column">
                <div class="h1" style="padding-bottom: 10px">more recent articles</div>
                <g:include controller="home" action="recent" params="[offsetRecent:3,maxRecent:4]"/>
            </div>
            <div style="clear:both"></div>
            <p />
        </div>
        <div id="footer-right-bottom-2">
            <div class="h1">articles by tag</div>
            <div style="padding-top: 7px; width:10px; float:left; padding-left: 20px">•</div><div style="padding-top: 7px; float:left; width:420px"><g:include controller="tag" action="show" params="[maxTags:9]"/></div>
        </div>
    </div>
</div>
<div style="clear:both; padding-top: 10px; margin-bottom: 10px; border-bottom: 1px solid #666633;"></div>
<p class="textdark" style="text-align: right">i built this blogging engine with groovy & <a class="brown" href="http://grails.org">grails</a> | <a class="brown" href="http://code.google.com/p/6footplus/">source code</a></p>
