<%@ page language="java" pageEncoding="UTF-8" %>
    <div class="nav1">导航菜单</div>
				<ul>
					<span>系统管理</span>
							<li><a href="${ctx}/system/user.action">帐号列表</a></li>
							<li><a href="${ctx}/system/organization.action">组织机构列表</a></li>
							<li><a href="${ctx}/system/role.action">角色列表</a></li>
							<li><a href="${ctx}/system/menu.action">菜单列表</a></li>
							<li><a href="${ctx}/system/log.action">日志列表</a></li>
				</ul>
				<ul>
					<span>订餐管理</span>
							<li><a href="${ctx}/eat/table.action">餐桌管理</a></li>
							<li><a href="${ctx}/eat/reserve.action">预订管理</a></li>
				</ul>
				<ul>						
					<span>菜品管理</span>
							<li><a href="${ctx}/eat/food.action">菜品管理</a></li>
							<li><a href="${ctx}/eat/dishes.action">点菜管理</a></li>
				</ul>
				<ul>			
					<span>基础数据</span>
							<li><a href="${ctx}/eat/receive.action">接收信息管理</a></li>
							<li><a href="${ctx}/eat/attention.action">邮件通知管理</a></li>
							<li><a href="${ctx}/eat/customer.action">顾客管理</a></li>
							<li><a href="${ctx}/eat/help.action">帮助信息管理</a></li>
							<li><a href="${ctx}/eat/help!input.action">修改帮助信息</a></li>
							<li><a href="${ctx}/eat/help!wxUrl.action">微信接口</a></li>
				</ul>
		</div>	
		
<script type="text/javascript">
window.onload = function() {
myMenu = new menu_zzjsnet("menu_zzjsnet",true);
myMenu.init();
myMenu2 = new menu_zzjsnet("menu2_zzjsnet");
myMenu2.init();
};
</script>			