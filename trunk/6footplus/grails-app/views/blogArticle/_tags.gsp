<g:if test="${flash.message}"><div class="message">${flash.message}</div></g:if>

<g:if test="${article?.tags}">tags: 
    <g:each var="tag" in="${article.tags}">• ${tag} <g:remoteLink action="ajaxDeleteTag" id="${article?.id}" params="'tag=${tag}'" update="tag_area_${article?.id}" class="light">x</g:remoteLink>&nbsp;</g:each>
</g:if>

<div id="tag-form">
    <g:formRemote name="newTag" update="tag_area_${article?.id}" url="[action:'ajaxSaveTag']">
        <input type="hiddenW" name="articleId" value="${article?.id}">
        <label for="label">Add a tag :</label><br/><g:textField style="width:150px" name="tag" value="" />
        <input type="submit" name="button" value="add" class="button"/>
    </g:formRemote>
</div>

