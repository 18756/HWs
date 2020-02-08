<#-- @ftlvariable name="users" type="ru.itmo.wp.domain.User[]" -->
<#-- @ftlvariable name="user" type="ru.itmo.wp.domain.User" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <th>Created</th>
                <#if user??>
                    <th>Disabled</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <#if users?size==0>
                <tr class="noData">
                    <td colspan="3">
                        No data
                    </td>
                </tr>
            <#else>
                <#list users as u>
                    <tr>
                        <td class="id">${u.id}</td>
                        <td class="login">${u.login}</td>
                        <td class="creationTime">${u.creationTime}</td>
                        <#if user??>
                            <td>
                                <form method="post" action="">
                                    <input type="hidden" name="action" value="users/all"/>
                                    <input type="hidden" name="userId" value="${u.id}"/>
                                    <#if u.disabled>
                                        <input type="hidden" name="newValue" value="0"/>
                                        <button>Enable</button>
                                    <#else>
                                        <input type="hidden" name="newValue" value="1"/>
                                        <button>Disable</button>
                                    </#if>
                                </form>
                            </td>
                        </#if>
                    </tr>
                </#list>
            </#if>
            </tbody>
        </table>
        <@c.error "updateDisabledForm.userId"/>
        <@c.error "updateDisabledForm.newValue"/>
    </div>
</@c.page>
