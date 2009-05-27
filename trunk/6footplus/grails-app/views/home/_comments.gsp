<g:set var="comments" value="${article?.comments?.sort{it.dateCreated}}"/>
<g:set var="index" value="${article?.id}"/>

<span id="comment_link_${index}" class="textdark2">• <g:remoteLink onComplete="Effect.BlindDown('show_comments_${index}'); return false;" action="ajaxShowComments" id="${article?.id}" update="comment_area_${index}" class="light">comments (${comments?.size()})</g:remoteLink></span>

<g:if test="${showComments}">
    <script type="text/javascript">$('comment_link_${index}').hide()</script>
    <div id="show_comments_${index}">
        <g:if test="${comments}">
            <div style="border: 1px solid #CCCC99; padding: 5px 5px 5px 5px">
                <g:each var="comment" in="${comments}"><span class="textdark2">•  ${comment.message}</span> <span class="textlight">(${comment.author})</span> <span class="date">(<g:formatDate format="dd MMM yyyy" date="${comment.dateCreated}"/>) <jsec:isLoggedIn>[<g:link controller="blogComment" action="delete" id="${comment.id}">delete</g:link>]</jsec:isLoggedIn></span><br/></g:each>
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
                <input type="submit" name="submitbutton" id="submitbutton" value="submit your comment" />
            </g:formRemote>
            <div style="padding-top:15px">• <a class="light" href="#" onclick="Effect.BlindUp('show_comments_${index}'); $('comment_link_${index}').appear({ duration: 1.5 }); return false;">close comments</a></div>        
        </div>
    </div>
</g:if>