<%@ page import="org.springframework.util.ClassUtils" %>
<%@ page import="org.codehaus.groovy.grails.plugins.searchable.SearchableUtils" %>
<%@ page import="org.codehaus.groovy.grails.plugins.searchable.lucene.LuceneUtils" %>
<%@ page import="org.codehaus.groovy.grails.plugins.searchable.util.StringQueryUtils" %>
<html>
    <head>
        <meta name="layout" content="simple"/>
        <style type="text/css">
            .title {
                margin: 1em 0;
                padding: .3em .5em;
                text-align: right;
                background-color: #999966;
                border-top: 1px solid #CCCC99;
            }
            .paging a.step {
                padding: 0 .3em;
            }
            .paging span.currentStep {
                font-weight: bold;
            }
            #searchbutton {
                margin-left: 5px;
                width: 160px;
                color: #666633;
                font-family: Arial;
                font-weight: normal;
                font-size: 12px;
            }

            #searchbutton:hover, #searchbutton a:hover{
                background-color:#666633;
                border:1px solid white;
                color:#ffffff;
            }
        </style>
        <script type="text/javascript">
            var focusQueryInput = function() {
                document.getElementById("q").focus();
            }
        </script>
    </head>
    <body>
        <div><h3>Search</h3><p>Find blog article(s) matching the desired search phrase (supports lucene <a href="http://lucene.apache.org/java/2_3_2/queryparsersyntax.html">query syntax</a>)</p></div>
        <div id="header">
            <g:form url='[controller: "searchable", action: "index"]' id="searchableForm" name="searchableForm" method="get">
                <g:textField name="q" value="${params.q}"/><input type="submit" id="searchbutton" value="Search" />
            </g:form>
            <div style="clear: both; display: none;" class="hint">See <a href="http://lucene.apache.org/java/2_3_2/queryparsersyntax.html">Lucene query syntax</a> for advanced queries</div>
        </div>
        <div id="main">
            <g:set var="haveQuery" value="${params.q?.trim()}" />
            <g:set var="haveResults" value="${searchResult?.results}" />
            <div class="title">
                <span>
                    <g:if test="${haveQuery && haveResults}">
                        Showing <strong>${searchResult.offset + 1}</strong> - <strong>${searchResult.results.size() + searchResult.offset}</strong> of <strong>${searchResult.total}</strong>
                        results for <strong>${params.q}</strong>
                    </g:if>
                    <g:else>&nbsp;</g:else>
                </span>
            </div>
            <g:if test="${haveQuery && !haveResults && !parseException}">
                <p>Nothing matched your query - <strong>${params.q}</strong></p>
            </g:if>
            <g:if test="${searchResult?.suggestedQuery}">
                <p>Did you mean <g:link controller="searchable" action="index" params="[q: searchResult.suggestedQuery]">${StringQueryUtils.highlightTermDiffs(params.q.trim(), searchResult.suggestedQuery)}</g:link>?</p>
            </g:if>
            <g:if test="${parseException}">
                <p>Your query - <strong>${params.q}</strong> - is not valid.</p>
                <p>Suggestions:</p>
                <ul>
                    <li>Fix the query: see <a href="http://lucene.apache.org/java/2_3_2/queryparsersyntax.html">Lucene query syntax</a> for examples</li>
                    <g:if test="${LuceneUtils.queryHasSpecialCharacters(params.q)}">
                        <li>Remove special characters like <strong>" - [ ]</strong>, before searching, eg, <em><strong>${LuceneUtils.cleanQuery(params.q)}</strong></em><br />
                            <em>Use the Searchable Plugin's <strong>LuceneUtils#cleanQuery</strong> helper method for this: <g:link controller="searchable" action="index" params="[q: LuceneUtils.cleanQuery(params.q)]">Search again with special characters removed</g:link></em>
                        </li>
                        <li>Escape special characters like <strong>" - [ ]</strong> with <strong>\</strong>, eg, <em><strong>${LuceneUtils.escapeQuery(params.q)}</strong></em><br />
                            <em>Use the Searchable Plugin's <strong>LuceneUtils#escapeQuery</strong> helper method for this: <g:link controller="searchable" action="index" params="[q: LuceneUtils.escapeQuery(params.q)]">Search again with special characters escaped</g:link></em><br />
                            <em>Or use the Searchable Plugin's <strong>escape</strong> option: <g:link controller="searchable" action="index" params="[q: params.q, escape: true]">Search again with the <strong>escape</strong> option enabled</g:link></em>
                        </li>
                    </g:if>
                </ul>
            </g:if>
            <g:if test="${haveResults}">
                <div class="results">
                    <g:each var="result" in="${searchResult.results}" status="index">
                        <div class="result">
                            <g:set var="className" value="${ClassUtils.getShortName(result.getClass())}" />
                            <g:if test="${className == 'BlogArticle'}">
                                <g:set var="link" value="${createLink(controller: 'home', action: 'show', id: result.id)}" />
                                <div><a href="${link}">${result.subject}</a> <span class="date">(<g:formatDate format="dd MMM yyyy" date="${result.dateCreated}"/>)</span></div>
                                <div style="margin-top: 10px">${result.body}</div>
                            </g:if>
                            <g:if test="${className =='Tag'}">
                                <div><g:link controller="home" params="[tag:result]">${result}</g:link> of type tag</div>
                            </g:if>
                            <div style="clear:both"></div>
                            <div style="margin-top: 10px" class="divider"></div>
                        </div>
                    </g:each>
                </div>
                <div>
                    <div class="paging">
                        <g:if test="${haveResults}">
                            Page:
                            <g:set var="totalPages" value="${Math.ceil(searchResult.total / searchResult.max)}" />
                            <g:if test="${totalPages == 1}"><span class="currentStep">1</span></g:if>
                            <g:else><g:paginate controller="searchable" action="index" params="[q: params.q]" total="${searchResult.total}" prev="&lt; previous" next="next &gt;"/></g:else>
                        </g:if>
                        <br/><br/>
                    </div>
                </div>
            </g:if>
        </div>
        <script>focusQueryInput();</script>
    </body>
</html>
