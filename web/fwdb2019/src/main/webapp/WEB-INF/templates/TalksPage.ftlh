<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#-- @ftlvariable name="talks" type="ru.itmo.wp.model.domain.Talk[]" -->
<#-- @ftlvariable name="error" type="java.lang.String" -->
<#-- @ftlvariable name="userService" type="ru.itmo.wp.model.repository.impl.UserRepositoryImpl" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="form-box">
        <div class="body">
            <form method="post" action="">
                <input type="hidden" name="action" value="sendMessage"/>
                <div class="field">
                    <div class="name">
                        <label for="chooseUser">Choose user</label>
                    </div>
                    <div class="value">
                        <select id="chooseUser" name="targetUserId">
                            <#list users as u>
                                <#if u.id != user.id>
                                    <option value="${u.id}">${u.login}</option>
                                </#if>
                            </#list>
                        </select>
                    </div>
                </div>
                <div class="field">
                    <div class="name messageForm">
                        <label for="message">Message</label>
                    </div>
                    <div class="value">
                        <textarea id="message" name="text"></textarea>
                    </div>
                </div>
                <#if error??>
                    <div class="error">
                        ${error!}
                    </div>
                </#if>
                <div class="button-field">
                    <input type="submit" value="Send message">
                </div>
            </form>
        </div>
    </div>

    <div class="datatable">
        <div class="caption">Messages</div>
        <table>
            <thead>
            <tr>
                <th>From</th>
                <th>to</th>
                <th>Message</th>
                <th>Time</th>
            </tr>
            </thead>
            <tbody>
            <#list talks as talk>
                <tr>
                    <td>${talk.getSourceUser().login}</td>
                    <td>${talk.getTargetUser().login}</td>
                    <td>${talk.text}</td>
                    <td>${talk.creationTime}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
</@c.page>
