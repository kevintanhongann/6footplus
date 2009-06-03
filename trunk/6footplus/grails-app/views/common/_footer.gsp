<div id="footer">
    <div id="footer-left">
        <div style="background: #999966"><img alt="" src="${createLinkTo(dir: 'images', file: 'surfertop_3.gif')}" border="0" /></div>
        <p class="textlight" style="padding: 7px;">herzlich willkommen! i&#39;m a german speaking south african currently living and working in munich, germany. i&#39;m a java, grails, groovy software developer and one might notice my site being a bit of a geek&#39;s sandpit. feel free to have a gander!</p>
        <p class="textlight" style="padding: 7px;">the 6footplus fella, <br/>andreas nerlich</p>
        <div style="padding-left: 7px; margin-bottom: 5px"><a title="My Friendfeed.com" href="http://friendfeed.com/6footplus"><img src="/images/friendfeed_32.png" width="22" border="0"/></a>&nbsp;&nbsp;&nbsp;<a href="/feed/rss"><img title="RSS" src="/images/feed_32.png" width="22" border="0"/></a>&nbsp;&nbsp;&nbsp;<a href="http://www.linkedin.com/in/andreasnerlich" title="My LinkedIn.com"><img src="/images/linkedin_32.png" width="22" border="0"/></a>&nbsp;&nbsp;&nbsp;<a href="http://twitter.com/6footplus" title="My Twitter.com"><img src="/images/twitter_32.png" border="0" width="22"/></a>&nbsp;&nbsp;&nbsp;<a href="http://vimeo.com/sixfootplus/videos" title="My Vimeo.com"><img src="/images/vimeo_32.png" width="22" border="0"/></a>&nbsp;&nbsp;&nbsp;<a href="http://www.flickr.com/photos/6footplus/" title="My Flickr.com"><img src="/images/flickr_32.png" border="0" width="22"/></a></div>
    </div>
    <div id="footer-right">    
        <div id="footer-right-top">
            <twitter:userLink class="title" name="6footplus" text="my recent tweets"/>
            <g:include controller="twitter" action="list" params="[maxTweets:3]"/>
            <p style="padding-left: 20px">• <twitter:userLink name="6footplus" text="follow me on twitter"/></p>
        </div>
        <div id="footer-right-middle">
            <div id="footer-right-middle-left">
                <a href="http://vimeo.com/sixfootplus/videos/" class="title">my recent videos</a>
                <g:include controller="rssReader" action="show" params="[producer:'vimeo']"/>
                <p style="padding-left: 20px">• <a href="http://vimeo.com/sixfootplus/videos/">more videos</a></p>
            </div>
            <div id="footer-right-middle-center">
                <a href="http://photos.6footplus.com/recent.php" class="title">my recent photos</a>
                <g:include controller="rssReader" action="show" params="[producer:'flickr']"/>
                <p style="padding-left: 20px">• <a href="http://photos.6footplus.com/recent.php">more photos</a></p>
            </div>
            <div id="footer-right-middle-right">
                <a href="http://twitpic.com/photos/6footplus" class="title">my recent twitpics</a>
                <g:include controller="rssReader" action="show" params="[producer:'twitpic']"/>
                <p style="padding-left: 20px">• <a href="http://twitpic.com/photos/6footplus">more twitpics</a></p>
            </div>
            <div style="clear:both"></div>
        </div>
        <div id="footer-right-bottom">
            <div class="h1">my contact details</div>
            <div style="margin-top:10px; padding-left: 20px; float:left; width:80px; height:85px"><img src="/images/6.gif" border="1" style="border-style: solid; border-color: rgb(102, 102, 51);"/></div>
            <div style="margin-top:10px; padding-left: 20px; float:left; width:150px">Andreas Nerlich<br />Web Developer<br />Munich, Germany<br />me&#64;6footplus.com<br /></div>
            <div style="clear:both"></div>
        </div>
    </div>
</div>
<div style="clear:both; margin-top: 10px; margin-bottom: 10px; border-bottom: 1px solid #666633;">&nbsp;</div>
<p class="date" style="text-align: right">built using groovy & grails</p>
