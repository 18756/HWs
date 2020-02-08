<#-- @ftlvariable name="user" type="ru.itmo.wp.domain.User" -->
<#import "commons.ftlh" as c>

<@c.page>
    <#if user??>
        <div class="users datatable">
            <div class="caption">User</div>
            <table>
                <tbody>
                <tr>
                    <td class="name">Id</td>
                    <td class="value">${user.id}</td>
                </tr>
                <tr>
                    <td class="name">Login</td>
                    <td class="value">${user.login}</td>
                </tr>
                <tr>
                    <td class="name">Created</td>
                    <td class="value">${user.creationTime}</td>
                </tr>
                </tbody>
            </table>
        </div>
    <#else>
        <h1>No such user</h1>
    </#if>
</@c.page>
