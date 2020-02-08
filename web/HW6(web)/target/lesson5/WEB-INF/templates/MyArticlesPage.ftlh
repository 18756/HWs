<#-- @ftlvariable name="myArticles" type="ru.itmo.wp.model.domain.Article[]" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="articles datatable">
        <div class="caption">My articles</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Title</th>
                <th>Hidden</th>
            </tr>
            </thead>
            <tbody>
            <#list myArticles as article>
                <tr>
                    <td class="article_id">${article.id}</td>
                    <td class="article_title">${article.title}</td>
                    <td class="article_hidden"><button data-id="${article.id}">${article.hidden}</button></td>
                </tr>
            </#list>
            </tbody>
        </table>

    </div>
    <div class="error">

    </div>

    <script>
        $(function () {
            $(".articles button").click(function () {
                $(".error").text("");
                let curEl = $(this);
                let articleId = curEl.data("id");
                console.log(articleId);
                let curValue  = curEl.text();
                let newValue;
                if (curValue === "show") {
                    newValue = "hide"
                } else {
                    newValue = "show"
                }
                window.ajax(
                    {
                        action:   "changeHidden",
                        articleId: articleId,
                        newValue:  newValue
                    },
                    function (response) {
                        if (response["error"]) {
                            $(".error").text(response["error"])
                        } else {
                            curEl.text(newValue);
                        }
                    }
                )
            });
        })
    </script>
</@c.page>
