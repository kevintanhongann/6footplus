<html>
    <head>
        <meta name="layout" content="simple"/>
        <title>6footplus.com - tag cloud</title>
    </head>
    <body>
        <style>
            .tag {
                height: 250px; background: #666633; border: 1px solid #BBBBBB; width: 680px;
            }
            .tagLink {
                text-decoration: none; margin-left: 5px; margin-right: 5px; color: #656565;
                color: white;
            }
            a.tagLink  {
                color: #FFFFCC;
            }
        </style>
        <span class="heading">tag cloud</span>
        <p>the bigger the tag name, the more there are articles tagged by this name</p>
        <p>
            <ui:tagCloud values="${tags}" class="tag" linkClass="tagLink" controller="home" action="index" sortField="key" sortOrder="desc" minSize="8" maxSize="50"/>
        </p>
    </body>
</html>

