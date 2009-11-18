<g:set var="comments" value="${article?.comments?.sort{it.dateCreated}}"/>
<g:set var="index" value="${article?.id}"/>

<span id="comment_link_${index}" class="textdark2">• <g:remoteLink onLoading="showSpinner('comment_spinner')" onLoaded="hideSpinner('comment_spinner')" onComplete="Effect.BlindDown('show_comments_${index}'); return false;" action="ajaxShowComments" id="${article?.id}" update="comment_area_${index}" class="light">comments (${comments?.size()})</g:remoteLink></span>
<div id="comment_spinner" style="display: none;"><p class="date"><img style="vertical-align:middle" src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Loading..."/> Loading...</p></div>

<g:if test="${showComments}">
    <script type="text/javascript">$('comment_link_${index}').hide()</script>
    <div id="show_comments_${index}">
        <g:if test="${comments}">
            <div style="border: 1px solid #CCCC99; padding: 5px 5px 5px 5px">
                <g:each var="comment" in="${comments}">
                    <div class="textdark2">•  <span class="textlight">${comment.author} </span> - ${comment.message} <span class="date">(<g:formatDate format="dd MMM yyyy" date="${comment.dateCreated}"/>)</span> <span class="date"><jsec:isLoggedIn>[<g:link controller="blogComment" action="delete" id="${comment.id}">delete</g:link>]</jsec:isLoggedIn></span></div>
                </g:each>
                <div style="margin-top: 10px;">•  <a class="light" href="#" onclick="Effect.BlindUp('show_comments_${index}'); $('comment_link_${index}').appear({ duration: 1.5 }); return false;">close comments</a></div>
            </div>
        </g:if>
        <g:elseif test="!${comments}">
            <div style="border: 1px solid #CCCC99; padding: 5px 5px 5px 5px">
                <span>•  no comments yet... be the first!</span>
                <div style="margin-top: 10px;">•  <a class="light" href="#" onclick="Effect.BlindUp('show_comments_${index}'); $('comment_link_${index}').appear({ duration: 1.5 }); return false;">close comments</a></div>
            </div>
        </g:elseif>
        <div class="commentBox">
            <g:if test="${flash.message}"><div class="message">${flash.message}</div></g:if>
            <g:eachError bean="${newComment}"><div class="error"><g:message error="${it}"/></div></g:eachError>
            <g:formRemote name="newComment" update="comment_area_${index}" url="[action:'ajaxSaveComment']">
                <input type="hidden" name="articleId" value="${article?.id}">
                <label for="author">Name :</label><g:textField name="author" value="${newComment?.author}" /><br/>
                <label for="message">Comment :</label><g:textArea name="message" value="${newComment?.message}" rows="5" cols="53"/><br/>
                <label for="message">&nbsp;</label>
                <div id="captchaChallenge"><cm:captchaRequest /></div>
                <label for="message">&nbsp;</label>
                <div id=captchaResponse">
                    <input id="captchaResponse" type="text" name="captcha" />
                </div>
                <input type="submit" name="submitbutton" id="submitbutton" value="submit your comment" />
            </g:formRemote>
            <div style="padding-top:15px">• <a class="light" href="#" onclick="Effect.BlindUp('show_comments_${index}'); $('comment_link_${index}').appear({ duration: 1.5 }); return false;">close comments</a></div>        
        </div>
    </div>
</g:if>
