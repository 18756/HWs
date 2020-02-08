<#-- @ftlvariable name="post" type="ru.itmo.wp.domain.Post" -->
<#-- @ftlvariable name="user" type="ru.itmo.wp.domain.User" -->
<#-- @ftlvariable name="comment" type="ru.itmo.wp.domain.Comment" -->
<#import "commons.ftlh" as c>
<@c.page>
    <#if post??>
        <@c.post post=post/>
        <#if user??>
            <div class="form comment-form">
                <div class="header">Write Comment</div>
                <div class="body">
                    <form method="post">
                        <input type="hidden" name="action" value=""/>
                        <div class="field">
                            <div class="name">
                                <label for="text">Text</label>
                            </div>
                            <div class="value">
                                <textarea id="text" name="text"></textarea>
                            </div>
                            <@c.error "comment.text"/>
                        </div>
                        <div class="button-field">
                            <input type="submit" value="Write">
                        </div>
                    </form>
                </div>
            </div>
        </#if>
        <#list post.comments as comment>
            <@c.comment comment=comment/>
        </#list>
    <#else>
        <h1>No such post</h1>
    </#if>
</@c.page>
