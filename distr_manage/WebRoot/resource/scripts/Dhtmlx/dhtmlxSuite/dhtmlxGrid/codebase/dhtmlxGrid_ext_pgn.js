function dhtmlxGridPaging(id) {

    // 当前页
    this.currentPage = 1;
    this.pageSize = GLOBAL_INFO.PAGING_DEFAULT;
    this.totalRecord = 0;
    this.pagingid = id;
    this.callback;

    /**
     * 初始分页组件参数
     * func 回调函数(必选)
     * pageSize 每页显示记录条数(可选)
     */
    this.initPaging = function (func, pageSize) {

        if (pageSize) {
            this.pageSize = pageSize;
        }

        this.drawTbar();

        this.callback = func;

        this.changePage();
    }

    this.drawTbar = function () {
        aToolBar = new dhtmlXToolbarObject(this.pagingid, "dhx_skyblue");

        var imgURL = GLOBAL_INFO.CONTEXTPATH + "/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/imgs/";

        // 添加按钮 上一页与第一页
        aToolBar.addButton("leftabs", NaN, "", imgURL + 'ar_left_abs.gif', imgURL + 'ar_left_abs_dis.gif');
        aToolBar.setWidth("leftabs", "20");
        aToolBar.addButton("left", NaN, "", imgURL + 'ar_left.gif', imgURL + 'ar_left_dis.gif');
        aToolBar.setWidth("left", "20");

        aToolBar.addText("results", NaN, '记录')
        aToolBar.setWidth("results", "150");
        aToolBar.disableItem("results");

        aToolBar.addButton("right", NaN, "", imgURL + 'ar_right.gif', imgURL + 'ar_right_dis.gif');
        aToolBar.setWidth("right", "20");
        aToolBar.addButton("rightabs", NaN, "", imgURL + 'ar_right_abs.gif', imgURL + 'ar_right_abs_dis.gif');
        aToolBar.setWidth("rightabs", "20");

        aToolBar.addButtonSelect("pages", NaN, "页数", [], null, null, true, true);
        aToolBar.setWidth("pages", "75");

        aToolBar.addButtonSelect("perpagenum", NaN, "每页条数", [], null, null, true, true);

        aToolBar.addListOption('perpagenum', 'perpagenum_' + this.pageSize, NaN, "button", this.pageSize);
        aToolBar.setWidth("perpagenum", "135");

        var self = this;

        // 添加事件
        aToolBar.attachEvent("onClick", function (val) {
            val = val.split("_");
            // 总页数
            var pageTotalNum = Math.ceil(self.totalRecord / self.pageSize);
            // alert('pageTotalNum='+pageTotalNum+'currentPage='+self.currentPage);
            switch (val[0]) {
                case "leftabs":
                    self.currentPage = 1;
                    self.changePage();
                    break;
                case "left":
                    self.currentPage = self.currentPage > 1 ? self.currentPage - 1 : self.currentPage;
                    self.changePage();
                    break;
                case "rightabs":
                    self.currentPage = pageTotalNum;
                    self.changePage();
                    break;
                case "right":
                    self.currentPage = self.currentPage + 1 > pageTotalNum ? pageTotalNum : self.currentPage + 1;
                    self.changePage();
                    break;
                case "perpagenum":
                    if (val[1] === this.undefined) return;
                    self.currentPage = 1;
                    self.changePage();
                    aToolBar.setItemText("perpagenum", "<div style='width:100%; text-align:right'>" + val[1] + "</div>");
                    break;
                case "pages":
                    if (val[1] === this.undefined) return;
                    self.currentPage = parseInt(val[1]);
                    self.changePage();
                    aToolBar.setItemText("pages", "<div style='width:100%; text-align:right'>" + val[1] + "</div>");
                    break;
            }
        })
    }

    this.changePage = function () {
        // alert(this.currentPage);
        var start = (this.currentPage - 1) * this.pageSize;

        var end = this.currentPage * this.pageSize;

        this.callback(start, end);

        // 因为考虑到当前项目都是异步请求,在此刷新toolbar不合适,另提供函数在异步请求成功后在调用setTotalPage有刷新功能
        // this.refresh();

        aToolBar.setItemText('results', "<div style='width:100%; text-align:center'>" + '记录从' + (start + 1) + '到' + end + "</div>");
        aToolBar.setItemText("pages", "<div style='width:100%; text-align:right'>" + this.currentPage + "</div>");
    }

    this.setTotalPage = function (t_page) {
        this.totalRecord = t_page;

        this.refresh();
    }

    this.refresh = function () {
        var totalPages = Math.ceil(this.totalRecord / this.pageSize);

        aToolBar.forEachListOption("pages", function (id) {
            aToolBar.removeListOption("pages", id);
        });

        for (var i = 0; i < totalPages; i++) {
            aToolBar.addListOption('pages', 'pages_' + (i + 1), NaN, "button", i + 1);
        }

        if (this.currentPage >= totalPages) {
            aToolBar.disableItem("right");
            aToolBar.disableItem("rightabs");
        }
        else {
            aToolBar.enableItem("right");
            aToolBar.enableItem("rightabs");
        }
        if (this.currentPage == 1) {
            aToolBar.disableItem("left");
            aToolBar.disableItem("leftabs");
        }
        else {
            aToolBar.enableItem("left");
            aToolBar.enableItem("leftabs");
        }
    }
}