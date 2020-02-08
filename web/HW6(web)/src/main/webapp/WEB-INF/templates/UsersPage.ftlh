<#-- @ftlvariable name="users" type="ru.itmo.wp.model.domain.User[]" -->
<#-- @ftlvariable name="user" type="ru.itmo.wp.model.domain.User" -->
<#-- @ftlvariable name="admin" type="java.lang.Boolean" -->
<#import "commons.ftlh" as c>

<@c.page>
    <div class="users datatable">
        <div class="caption">User</div>
        <table>
            <thead>
            <tr>
                <th>Id</th>
                <th>Login</th>
                <#if admin??>
                    <th>Admin</th>
                </#if>
            </tr>
            </thead>
            <tbody>
            <template>
                <tr>
                    <td class="user_id"></td>
                    <td class="user_login"></td>
                    <#if admin??>
                        <td class="user_admin"><span class="text_admin"></span>
                            <button class="button_admin"></button>
                        </td>
                    </#if>
                </tr>
            </template>
            <tr class="noData">
                <td colspan="3">
                    No data
                </td>
            </tr>
            </tbody>
        </table>
    </div>

    <div class="error">

    </div>

    <script>
        $(function () {
            var $noData = $(".users .noData");
            $noData.find("td").text("Loading...");

            window.ajax(
                {
                    action: "findAll"
                },
                function (response) {
                    $noData.hide();
                    response["users"].forEach(function (user) {
                        var $tr = $($(".users template").prop("content")).clone();
                        $tr.find(".user_id").text(user["id"]);
                        $tr.find(".user_login").text(user["login"]);
                        if (user["admin"]) {
                            $tr.find(".text_admin").text("true");
                            $tr.find(".button_admin").text("disable");
                        } else {
                            $tr.find(".text_admin").text("false");
                            $tr.find(".button_admin").text("enable");
                        }
                        $tr.find(".button_admin").attr("data-userId", user["id"]);
                        $tr.find("tr").attr("data-userId", user["id"]);
                        $(".users tbody").append($tr);
                    });
                    $(".users tr .button_admin").click(function () {
                        $(".error").text("");
                        let curEl = $(this).parent();
                        let curButtonText = $(this).text();
                        let newButtonText;
                        let newSpanText;
                        let newValue;
                        if (curButtonText === "enable") {
                            newSpanText = "true";
                            newButtonText = "disable";
                            newValue = "1";
                        } else {
                            newSpanText = "false";
                            newButtonText = "enable";
                            newValue = "0";
                        }
                        window.ajax(
                            {
                                action: "updateAdmin",
                                userId: $(this).attr("data-userId"),
                                newValue: newValue
                            },
                            function (response) {
                                if (response["error"]) {
                                    $(".error").text(response["error"])
                                } else {
                                    curEl.find(".button_admin").text(newButtonText);
                                    curEl.find(".text_admin").text(newSpanText);
                                    notify("admin property changed");
                                }
                                <#if user??>
                                if (curEl.find(".button_admin").attr("data-userId") === "${user.id}") {
                                    location.reload();
                                }
                                </#if>
                            }
                        );
                        return false;
                    });
                    $(".users tr").click(function () {
                        window.ajax(
                            {
                                action: "findUser",
                                userId: $(this).attr("data-userId")
                            },
                            function (response) {
                                notify(response["user"].creationTime);
                            }
                        );
                    });
                }
            );
        })
    </script>
</@c.page>
