<div class="page">
	<s:text name="amount.page"><s:param>${page.pageNo}</s:param><s:param>${page.totalPages}</s:param></s:text>
	<a href="javascript:jumpPage(1)"><s:text name="first.page"/></a>
	<s:if test="page.hasPre"><a href="javascript:jumpPage(${page.prePage})"><s:text name="prevpage"/></a></s:if>
	<s:if test="page.hasNext"><a href="javascript:jumpPage(${page.nextPage})"><s:text name="nextpage"/></a></s:if>
	<a href="javascript:jumpPage(${page.totalPages})"><s:text name="last.page"/></a>
</div>