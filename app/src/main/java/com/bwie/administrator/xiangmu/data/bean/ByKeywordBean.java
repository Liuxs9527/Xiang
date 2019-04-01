package com.bwie.administrator.xiangmu.data.bean;

import java.util.List;

public class ByKeywordBean {

    /**
     * result : [{"commodityId":136,"commodityName":"板鞋休闲鞋男男士休闲运动鞋男士鞋子休闲皮鞋男士休闲鞋男鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/nbx/2/1.jpg","price":99,"saleNum":0},{"commodityId":144,"commodityName":"冬季新款高帮帆布鞋加绒保暖平底百搭时尚气质绑带字母经典款帆布鞋男士系带休闲靴","masterPic":"http://172.17.8.100/images/small/commodity/nx/nfbx/3/1.jpg","price":148,"saleNum":0},{"commodityId":152,"commodityName":"皮鞋男真皮黑色透气男士休闲皮鞋男鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/px/4/1.jpg","price":119,"saleNum":0},{"commodityId":160,"commodityName":"简约百搭商务休闲鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/swxxx/5/1.jpg","price":459,"saleNum":0},{"commodityId":168,"commodityName":"李宁 狼塔低帮 男款跑步鞋ARHN205耐磨防滑运动鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/ydx/6/1.jpg","price":298,"saleNum":0},{"commodityId":141,"commodityName":"AUXTUN港仔原宿男鞋秋季鞋子男潮鞋百搭韩版潮流男士休闲鞋板鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/nbx/7/1.jpg","price":99,"saleNum":0},{"commodityId":149,"commodityName":"新品蛇纹商务正装系带皮鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/px/1/1.jpg","price":258,"saleNum":0},{"commodityId":157,"commodityName":"舒适百搭套脚商务休闲鞋男士皮鞋男鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/swxxx/2/1.jpg","price":249,"saleNum":0},{"commodityId":165,"commodityName":"特步XTEP 减震耐磨 男款全掌气垫跑鞋运动鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/ydx/3/1.jpg","price":199,"saleNum":0},{"commodityId":138,"commodityName":"秋男鞋时尚男士休闲鞋系带防磨脚男士皮鞋潮流休闲板鞋","masterPic":"http://172.17.8.100/images/small/commodity/nx/nbx/4/1.jpg","price":449,"saleNum":0}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commodityId : 136
         * commodityName : 板鞋休闲鞋男男士休闲运动鞋男士鞋子休闲皮鞋男士休闲鞋男鞋
         * masterPic : http://172.17.8.100/images/small/commodity/nx/nbx/2/1.jpg
         * price : 99
         * saleNum : 0
         */

        private int commodityId;
        private String commodityName;
        private String masterPic;
        private int price;
        private int saleNum;

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public String getMasterPic() {
            return masterPic;
        }

        public void setMasterPic(String masterPic) {
            this.masterPic = masterPic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(int saleNum) {
            this.saleNum = saleNum;
        }
    }
}
