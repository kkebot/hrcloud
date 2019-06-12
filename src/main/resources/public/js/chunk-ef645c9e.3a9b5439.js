(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-ef645c9e"],{"05b5":function(t,e,a){},"19e3":function(t,e,a){"use strict";var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("Row",{attrs:{type:"flex",align:"middle",gutter:8}},[t.labelText?a("Col",{attrs:{span:t.labelSpan}},[a("div",{style:!t.labelSpan&&{width:t.labelWidth+"px"}},[a("span",[t._v(t._s(t.labelText))])])]):t._e(),a("Col",[a("Upload",{ref:"upload",attrs:{"show-upload-list":!1,action:"",accept:t.accept,"before-upload":t.handleUpload}},[a("Button",{attrs:{disabled:t.disabled,icon:"md-arrow-up",type:t.color}},[t._v(t._s(t.buttonHint))])],1)],1),t.file?a("Col",[a("Button",{attrs:{size:"small",icon:"md-close"},on:{click:function(e){t.file=null}}})],1):t._e()],1),t.file&&t.showSelected?a("div",[a("br"),a("Alert",[t._v("已选择文件:"+t._s(t.file.name))])],1):t._e()],1)},o=[],r=(a("c5f6"),{name:"UploadButton",props:{labelText:{type:String,default:"上传文件"},labelWidth:{type:Number,default:50},accept:{type:String,default:".txt"},buttonText:{type:String,default:"选择本地文件"},showSelected:{type:Boolean,default:!0},disabled:{type:Boolean,default:!1},labelSpan:{type:Number,default:null},color:{type:String,default:null}},computed:{buttonHint:function(){return this.file?"重新选择":this.buttonText}},data:function(){return{file:null,labelClazz:{width:this.labelWidth}}},methods:{handleUpload:function(t){return this.file=t,this.$emit("on-file-ready",t),!1},reset:function(){this.file=null}}}),i=r,l=a("2877"),s=Object(l["a"])(i,n,o,!1,null,"7487a8df",null);e["a"]=s.exports},5322:function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("Card",[a("Divider",{attrs:{orientation:"left"}},[t._v("添加岗位")]),a("position-form",{attrs:{"use-button":!0},on:{"on-form-ready":t.onSetup}})],1)},o=[],r=a("b6e0"),i=a("6e6f"),l={components:{PositionForm:r["a"]},data:function(){return{administrationId:this.$route.query.departmentId,administration:null}},methods:{onSetup:function(t){var e=this,a=this.administrationId;Object(i["e"])(a,t).then(function(t){console.log({res:t});var a=t.data.id;e.$Notice.success({title:"设置岗位成功"}),e.$router.push({name:"PositionDetails",params:{id:a}})}).catch(function(t){return e.$Notice.error({title:"设置岗位失败(".concat(t.status,")"),desc:t.data.message})})}}},s=l,u=(a("8083"),a("2877")),c=Object(u["a"])(s,n,o,!1,null,"4acaadf7",null);e["default"]=c.exports},"6e6f":function(t,e,a){"use strict";a.d(e,"e",function(){return o}),a.d(e,"c",function(){return r}),a.d(e,"d",function(){return i}),a.d(e,"b",function(){return l}),a.d(e,"a",function(){return s}),a.d(e,"f",function(){return u});var n=a("b775"),o=function(t,e){return n["a"].post("/api/position",e,{params:{departmentId:t}})},r=function(t){return n["a"].get("/api/position/".concat(t))},i=function(t){return n["a"].get("/api/position/".concat(t,"/details"))},l=function(t,e,a){return n["a"].get("/api/position/".concat(t,"/adjustments"),{params:{page:e,size:a}})},s=function(t){return n["a"].post("/api/position/cancel","",{params:{positionId:t}})},u=function(t,e){return n["a"].post("/api/position/update",e,{params:{positionId:t}})}},8083:function(t,e,a){"use strict";var n=a("05b5"),o=a.n(n);o.a},b6e0:function(t,e,a){"use strict";var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("Form",{ref:"form",attrs:{"label-position":"left","label-width":80,model:t.formData,rules:t.ruleValidate}},[a("FormItem",{attrs:{label:"职务名称",prop:"name"}},[a("Input",{model:{value:t.formData.name,callback:function(e){t.$set(t.formData,"name",e)},expression:"formData.name"}})],1),a("FormItem",{attrs:{label:"职务类型",prop:"type"}},[a("Select",{attrs:{disabled:!!t.position},model:{value:t.formData.type,callback:function(e){t.$set(t.formData,"type",e)},expression:"formData.type"}},t._l(t.types,function(e){return a("Option",{key:e,attrs:{value:e}},[t._v(t._s(e))])}),1)],1),a("FormItem",{attrs:{label:"编制人数",prop:"expectedCount"}},[a("InputNumber",{model:{value:t.formData.expectedCount,callback:function(e){t.$set(t.formData,"expectedCount",e)},expression:"formData.expectedCount"}})],1),a("FormItem",{attrs:{label:"创建时间",prop:"createdAt"}},[a("DatePicker",{attrs:{type:"datetime",editable:!1},model:{value:t.formData.createdAt,callback:function(e){t.$set(t.formData,"createdAt",e)},expression:"formData.createdAt"}})],1),a("FormItem",{attrs:{label:"描述",prop:"description"}},[a("Input",{model:{value:t.formData.description,callback:function(e){t.$set(t.formData,"description",e)},expression:"formData.description"}})],1)],1),a("upload-button",{ref:"upload",attrs:{accept:".doc,.docx","label-width":80},on:{"on-file-ready":function(e){t.formData.file=e}}}),t.useButton?a("div",[a("br"),a("Button",{attrs:{type:"success",disabled:!t.isReady},on:{click:t.onOk}},[t._v("确定")])],1):t._e()],1)},o=[],r=a("cebc"),i=(a("7f7f"),a("2934")),l=a("19e3"),s={name:"PositionForm",components:{UploadButton:l["a"]},props:["position","useButton"],computed:{isReady:function(){return!!this.formData.name&&!!this.formData.createdAt&&!!this.formData.type&&!!this.formData.expectedCount}},data:function(){return{types:["行政","管理","后勤","技术"],ruleValidate:{name:[{required:!0,message:"请输入名称",trigger:"blur"}],type:[{required:!0,message:"请选择职务类型"}],expectedCount:[{required:!0,message:"请输入人数"}],createdAt:[{required:!0,message:"请输入创建时间"}]},formData:Object(r["a"])({},this.defaultForm(),this.position)}},methods:{reset:function(){this.formData=this.defaultForm(),this.$refs.upload.reset()},defaultForm:function(){return{name:null,type:null,expectedCount:1,createdAt:new Date,file:null,description:null}},onOk:function(){var t=this;this.$refs.form.validate(function(e){e&&(t.$emit("on-form-ready",Object(i["b"])(t.formData)),t.reset())})}}},u=s,c=a("2877"),d=Object(c["a"])(u,n,o,!1,null,null,null);e["a"]=d.exports}}]);
//# sourceMappingURL=chunk-ef645c9e.3a9b5439.js.map