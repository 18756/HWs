<#import "commons.ftlh" as c>

<@c.page>
    Welcome on Codeforces!

    <div class="articles">
        <template>
            <article>
                <div class="title"></div>
                <div class="information"></div>
                <div class="body">
                    <p class="text"></p>
                </div>
                <div class="footer">
                    <div class="left">
                        <img src="img/voteup.png" title="Vote Up" alt="Vote Up"/>
                        <span class="positive-score">+173</span>
                        <img src="img/votedown.png" title="Vote Down" alt="Vote Down"/>
                    </div>
                    <div class="right">
                        <img src="img/date_16x16.png" title="Publish Time" alt="Publish Time"/>
                        <span class="date"></span>
                        <img src="img/comments_16x16.png" title="Comments" alt="Comments"/>
                        <a href="#">68</a>
                    </div>
                </div>
            </article>
        </template>
    </div>

    <script>
        $(function () {
            window.ajax(
                {
                    action: "findArticles"
                },
                function (response) {
                    response["articles"].forEach(function (article) {
                        var $art = $($(".articles template").prop("content")).clone();
                        $art.find(".title").text(article["title"]);
                        $art.find(".information").text("Author: " + article["userLogin"]);
                        $art.find(".text").text(article["text"]);
                        $art.find(".date").text(article["creationTime"].match(/([^0-9]+[0-9]+){2}/)[0]);
                        $(".articles").append($art);
                    });
                }
            );
        })
    </script>
</@c.page>
