(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-f9b2b916"],{"19e3":function(t,e,a){"use strict";var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("Row",{attrs:{type:"flex",align:"middle",gutter:8}},[t.labelText?a("Col",{attrs:{span:t.labelSpan}},[a("div",{style:!t.labelSpan&&{width:t.labelWidth+"px"}},[a("span",[t._v(t._s(t.labelText))])])]):t._e(),a("Col",[a("Upload",{ref:"upload",attrs:{"show-upload-list":!1,action:"",accept:t.accept,"before-upload":t.handleUpload}},[a("Button",{attrs:{disabled:t.disabled,icon:"md-arrow-up",type:t.color}},[t._v(t._s(t.buttonHint))])],1)],1),t.file?a("Col",[a("Button",{attrs:{size:"small",icon:"md-close"},on:{click:function(e){t.file=null}}})],1):t._e()],1),t.file&&t.showSelected?a("div",[a("br"),a("Alert",[t._v("已选择文件:"+t._s(t.file.name))])],1):t._e()],1)},o=[],r=(a("c5f6"),{name:"UploadButton",props:{labelText:{type:String,default:"上传文件"},labelWidth:{type:Number,default:50},accept:{type:String,default:".txt"},buttonText:{type:String,default:"选择本地文件"},showSelected:{type:Boolean,default:!0},disabled:{type:Boolean,default:!1},labelSpan:{type:Number,default:null},color:{type:String,default:null}},computed:{buttonHint:function(){return this.file?"重新选择":this.buttonText}},data:function(){return{file:null,labelClazz:{width:this.labelWidth}}},methods:{handleUpload:function(t){return this.file=t,this.$emit("on-file-ready",t),!1},reset:function(){this.file=null}}}),l=r,s=a("2877"),i=Object(s["a"])(l,n,o,!1,null,"7487a8df",null);e["a"]=i.exports},"1a4e":function(t,e,a){"use strict";var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("Form",{ref:"form",attrs:{"label-width":100,"label-position":"left",model:t.formData}},[a("FormItem",{staticClass:"form-label-font",attrs:{label:"生效时间",prop:"effectiveOn"}},[a("DatePicker",{attrs:{type:"datetime",editable:!1},model:{value:t.formData.effectiveOn,callback:function(e){t.$set(t.formData,"effectiveOn",e)},expression:"formData.effectiveOn"}})],1),a("FormItem",{staticClass:"form-label-font",attrs:{label:"描述文字",prop:"description"}},[a("Input",{model:{value:t.formData.description,callback:function(e){t.$set(t.formData,"description",e)},expression:"formData.description"}})],1)],1),a("upload-button",{ref:"upload",attrs:{accept:".doc,.docx","label-width":100},on:{"on-file-ready":function(e){t.formData.file=e}}}),t.useButton?a("div",[a("br"),a("Button",{attrs:{type:"success"},on:{click:t.onOk}},[t._v("确定")])],1):t._e()],1)},o=[],r=(a("6b54"),a("19e3")),l=a("2934"),s={name:"SimpleChangeForm",components:{UploadButton:r["a"]},props:{useButton:{type:Boolean,default:!1},effectiveOn:{type:String,default:function(){return(new Date).toString()}},description:{type:String,default:""}},data:function(){return{formData:this.defaultForm()}},methods:{defaultForm:function(){return{effectiveOn:new Date(this.effectiveOn),description:this.description,file:null}},reset:function(){this.formData=this.defaultForm(),this.$refs.upload.reset()},getFormData:function(){var t=this.formData;return Object(l["b"])(t)},onOk:function(){this.$emit("on-form-ready",this.getFormData()),this.reset()},debugPrint:function(){var t=this.effectiveOn,e=this.formData;console.log({effectiveOn:t,formData:e})}}},i=s,c=(a("fa80"),a("2877")),u=Object(c["a"])(i,n,o,!1,null,"952f6d5e",null);e["a"]=u.exports},"1c05":function(t,e,a){"use strict";a.r(e);var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("Card",[a("employee-profile",{attrs:{"employee-id":t.userId}})],1)},o=[],r=a("2efd"),l=a("c38b"),s={components:{EmployeeProfile:r["a"]},name:"Profile",data:function(){return{userId:Object(l["a"])()}}},i=s,c=a("2877"),u=Object(c["a"])(i,n,o,!1,null,"e56578c2",null);e["default"]=u.exports},"20d6":function(t,e,a){"use strict";var n=a("5ca1"),o=a("0a49")(6),r="findIndex",l=!0;r in[]&&Array(1)[r](function(){l=!1}),n(n.P+n.F*l,"Array",{findIndex:function(t){return o(this,t,arguments.length>1?arguments[1]:void 0)}}),a("9c6c")(r)},"2efd":function(t,e,a){"use strict";var n=function(){var t=this,e=t.$createElement,a=t._self._c||e;return t.employee?a("div",[a("Row",{attrs:{type:"flex",gutter:25,align:"middle"}},[a("Col",{attrs:{span:"16"}},[a("div",[a("Divider",{attrs:{orientation:"left"}},[t._v("基础信息")]),a("Row",{attrs:{type:"flex",align:"middle"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("姓名")])]),a("Col",[t._v(t._s(t.employee.name))])],1),a("br"),a("Row",{attrs:{type:"flex",align:"middle"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("编号")])]),a("Col",[t._v(t._s(t.employee.id))])],1),a("br"),a("Row",{attrs:{type:"flex",align:"middle"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("性别")])]),a("Col",[t._v(t._s(t.employee.gender))])],1),a("br"),a("Row",{attrs:{type:"flex",align:"middle"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("证件号码")])]),a("Col",[t._v(t._s(t.employee.credential))])],1),a("br"),a("Row",{attrs:{type:"flex",align:"middle"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("出生日期")])]),a("Col",[t._v(t._s(t._f("formatDate")(t.employee.birth)))])],1),a("br"),a("Row",{attrs:{type:"flex",align:"middle"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("年龄")])]),a("Col",[t._v(t._s(t.age))])],1)],1)]),a("Col",{attrs:{span:"8"}},[t.avatarUrl?[a("div",{staticClass:"avatar-container"},[a("img",{staticClass:"avatar-image",attrs:{src:t.avatarUrl}})]),a("div",{staticStyle:{"text-align":"center"}},[a("br"),a("span",[t._v("个人照片")])])]:[a("div",{staticClass:"avatar-container"},[a("Alert",{attrs:{"show-icon":""}},[a("template",{slot:"desc"},[t._v("无个人照片")])],2)],1)]],2)],1),a("br"),a("Row",{attrs:{type:"flex",gutter:25}},[a("Col",{attrs:{span:"12"}},[a("div",[a("Divider",{attrs:{orientation:"left"}},[t._v("任职信息")]),a("Row",{attrs:{type:"flex",align:"middle"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("入职时间")])]),a("Col",[t._v(t._s(t._f("formatDate")(t.employee.entryTime)))])],1),a("br"),a("Row",{attrs:{type:"flex",align:"middle"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("当前状态")])]),a("Col",[a("Tag",{attrs:{color:t.employee.status?"success":"error"}},[t._v("\n              "+t._s(t.employee.status?"在职":"离职")+"\n            ")])],1)],1),a("br"),a("Row",{attrs:{type:"flex",align:"middle"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("司龄")])]),a("Col",[a("Tag",[t._v(t._s(t.servingAge))])],1)],1),a("br"),a("Row",{attrs:{type:"flex",align:"middle"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("当前职务")])]),t.employee.mainPosition?a("Col",{attrs:{span:"20"}},[a("Row",{attrs:{type:"flex",align:"middle",justify:"space-between"}},[a("Col",[a("Tag",{attrs:{color:"success"}},[t._v("\n                  "+t._s(t._f("formatPosition")(t.employee.mainPosition))+"\n                ")])],1),a("Col",[a("Button",{attrs:{type:"primary",size:"small",icon:"md-arrow-forward",to:{name:"PositionDetails",params:{id:t.employee.mainPosition.id}}}})],1)],1)],1):t._e()],1),a("br"),a("Row",{attrs:{type:"flex",align:"middle"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("薪资级别")])]),t.employee.scale?a("Col",{attrs:{span:"20"}},[a("Tag",{attrs:{color:"primary"}},[t._v("\n              "+t._s(t._f("formatScale")(t.employee.scale))+"\n            ")])],1):t._e()],1),a("br"),a("Row",{attrs:{type:"flex"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("招聘方式")])]),a("Col",[t._v(t._s(t.employee.recruitment))])],1)],1)]),a("Col",{attrs:{span:"12"}},[a("div",[a("Divider",{attrs:{orientation:"left"}},[t._v("联系方式")]),a("Row",{attrs:{type:"flex"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("住址")])]),a("Col",[t._v(t._s(t.employee.address))])],1),a("br"),a("Row",{attrs:{type:"flex"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("电子邮箱")])]),a("Col",[t._v(t._s(t.employee.email))])],1),a("br"),a("Row",{attrs:{type:"flex",gutter:10}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("手机号码")])]),t.employee.phoneNumbers.length>0?a("Col",{attrs:{span:"8"}},[a("Collapse",[a("Panel",[a("span",[t._v(t._s(t.employee.phoneNumbers[0]))]),a("template",{slot:"content"},[a("br"),t._l(t.employee.phoneNumbers,function(e){return a("div",[a("p",[a("Icon",{attrs:{type:"md-call"}}),t._v("\n                      "+t._s(e)+"\n                    ")],1),a("br")])})],2)],2)],1)],1):t._e()],1),a("br")],1)])],1),a("br"),a("Row",{attrs:{type:"flex",gutter:25}},[a("Col",{attrs:{span:"12"}},[a("div",[a("Divider",{attrs:{orientation:"left"}},[t._v("教育信息")]),a("Row",{attrs:{type:"flex"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("学历")])]),a("Col",[t._v(t._s(t.employee.education))])],1),a("br"),a("Row",{attrs:{type:"flex"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("毕业院校")])]),a("Col",[t._v(t._s(t.employee.college))])],1),a("br"),a("Row",{attrs:{type:"flex"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("专业")])]),a("Col",[t._v(t._s(t.employee.major))])],1),a("br"),a("Row",{attrs:{type:"flex"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("毕业时间")])]),a("Col",[t._v(t._s(t._f("formatDate")(t.employee.graduateTime)))])],1),a("br"),a("Row",{attrs:{type:"flex"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("学位")])]),a("Col",[t._v(t._s(t.employee.degree))])],1)],1)]),a("Col",{attrs:{span:"12"}},[a("div",[a("Divider",{attrs:{orientation:"left"}},[t._v("其他信息")]),a("Row",{attrs:{type:"flex"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("婚姻状态")])]),a("Col",[t._v(t._s(t.employee.marriage))])],1),a("br"),a("Row",{attrs:{type:"flex"}},[a("Col",{attrs:{span:"4"}},[a("label",[t._v("政治面貌")])]),a("Col",[t._v(t._s(t.employee.politicalStatus))])],1)],1)])],1),a("br"),a("Row",{attrs:{type:"flex",gutter:25}},[a("Col",{attrs:{span:"12"}},[a("div",[a("Divider",{attrs:{orientation:"left"}},[t._v("附件")]),a("Collapse",[a("Panel",[t._v("\n            下载文件\n            "),t.attachments.length?a("div",{attrs:{slot:"content"},slot:"content"},[t._l(t.attachments,function(e){return[a("Row",{attrs:{type:"flex",align:"middle"}},[a("Col",{attrs:{span:"6"}},[a("label",[t._v(t._s(e.type))])]),a("Col",[a("Button",{attrs:{icon:"md-download",size:"small"},on:{click:function(a){return t.download(e.resource.id)}}})],1)],1),a("br")]})],2):a("div",{attrs:{slot:"content"},slot:"content"},[t._v("\n              无附件\n            ")])])],1)],1)])],1),a("Divider"),a("Row",{attrs:{type:"flex",justify:"space-between",align:"middle"}},[a("Col",[a("ButtonGroup",[a("Button",{attrs:{to:{name:"EmployeeUpdate",query:{employeeId:t.employeeId}}}},[t._v("更新个人信息")]),t.isAdmin?a("Button",{attrs:{disabled:!t.employee.status,type:"primary",to:{name:"EmployeeAdjustment",query:{employeeId:t.employeeId}}}},[t._v("调整职务/薪级\n        ")]):t._e()],1)],1),t.isAdmin?a("Col",[t.employee.status?[a("Button",{attrs:{type:"error"},on:{click:function(e){t.dischargeModal=!0}}},[t._v("解除聘用")])]:[a("Button",{attrs:{type:"success"},on:{click:t.restore}},[t._v("恢复聘用")])]],2):t._e()],1),a("Modal",{attrs:{title:"解除聘用","footer-hide":!0},model:{value:t.dischargeModal,callback:function(e){t.dischargeModal=e},expression:"dischargeModal"}},[a("simple-change-form",{ref:"dischargeForm"}),a("br"),a("Alert",{attrs:{type:"warning","show-icon":""}},[t._v("\n      警告\n      "),a("template",{slot:"desc"},[t._v("\n        该操作将会解除员工当前职务和撤销其薪资级别\n      ")])],2),a("Button",{attrs:{long:"",type:"error"},on:{click:t.discharge}},[t._v("解除聘用")])],1)],1):t._e()},o=[],r=(a("7514"),a("ef75")),l=a("edc0"),s=a("1a4e"),i=a("6c6e"),c=a("8454"),u=a("ed08"),p={name:"EmployeeProfile",components:{SimpleChangeForm:s["a"]},props:["employeeId"],inject:["reload"],data:function(){return{isAdmin:Object(u["c"])(["ADMIN"]),employee:null,dischargeModal:!1,attachments:[]}},computed:{servingAge:function(){var t=Object(l["a"])(this.employee.entryTime,new Date),e=t.year,a=t.month,n=t.day;return"".concat(e,"年 ").concat(a,"个月 ").concat(n,"日")},age:function(){return Object(l["a"])(this.employee.birth,new Date).year},avatarUrl:function(){var t=this.attachments.find(function(t){return"个人照片"===t.type});return t&&Object(i["b"])(t.resource.id)}},mounted:function(){var t=this;Object(r["g"])(this.employeeId).then(function(e){return t.employee=e.data}),Object(r["e"])(this.employeeId).then(function(e){t.attachments=e.data})},methods:{discharge:function(){var t=this;this.dischargeModal=!1;var e=this.$refs.dischargeForm.getFormData(),a=this.employeeId;Object(r["c"])(a,e).then(function(e){t.$Notice.success({title:"解除聘用成功(员工编号".concat(a,")")}),t.reload()}).catch(function(e){return t.$Notice.error({title:"解除聘用失败(".concat(e.status,")"),desc:e.data.message})})},download:function(t){Object(i["a"])(t)},restore:function(){var t=this;Object(r["k"])(this.employeeId).then(function(e){t.$Notice.success({title:"恢复聘用成功"}),t.reload()}).catch(function(e){return t.$Notice.error({title:"恢复聘用失败".concat(e.status),desc:e.data.message})})}},filters:{formatDate:c["b"],formatScale:c["h"],formatPosition:c["e"]}},d=p,f=(a("f194"),a("2877")),m=Object(f["a"])(d,n,o,!1,null,"423b50fc",null);e["a"]=m.exports},3846:function(t,e,a){a("9e1e")&&"g"!=/./g.flags&&a("86cc").f(RegExp.prototype,"flags",{configurable:!0,get:a("0bfb")})},"6b54":function(t,e,a){"use strict";a("3846");var n=a("cb7c"),o=a("0bfb"),r=a("9e1e"),l="toString",s=/./[l],i=function(t){a("2aba")(RegExp.prototype,l,t,!0)};a("79e5")(function(){return"/a/b"!=s.call({source:"a",flags:"b"})})?i(function(){var t=n(this);return"/".concat(t.source,"/","flags"in t?t.flags:!r&&t instanceof RegExp?o.call(t):void 0)}):s.name!=l&&i(function(){return s.call(this)})},"96c9":function(t,e,a){},e8e3:function(t,e,a){},edc0:function(t,e,a){"use strict";a.d(e,"b",function(){return n}),a.d(e,"a",function(){return o});a("20d6");var n=function(t){return Math.round(100*t)/100},o=function(t,e){var a=[1,3,5,7,8,10,12,4,6,9,11,2],n=new Date(t),o=new Date(e),r=o.getFullYear()-n.getFullYear(),l=o.getMonth()-n.getMonth(),s=o.getDate()-n.getDate();if(l<0&&(r--,l=o.getMonth()+(12-n.getMonth())),s<0){l--;var i,c=a.findIndex(function(t){return t===n.getMonth()+1});i=c<=6?31:c>6&&c<=10?30:28,s=o.getDate()+(i-n.getDate())}return{year:r,month:l,day:s}}},ef75:function(t,e,a){"use strict";a.d(e,"i",function(){return o}),a.d(e,"g",function(){return r}),a.d(e,"h",function(){return l}),a.d(e,"j",function(){return s}),a.d(e,"k",function(){return i}),a.d(e,"m",function(){return c}),a.d(e,"e",function(){return u}),a.d(e,"f",function(){return p}),a.d(e,"a",function(){return d}),a.d(e,"d",function(){return f}),a.d(e,"c",function(){return m}),a.d(e,"l",function(){return y}),a.d(e,"b",function(){return v});var n=a("b775"),o=function(t,e,a,o){return n["a"].get("/api/employee/query",{params:{entityType:t,entityId:e,page:a,size:o}})},r=function(t){return n["a"].get("/api/employee/".concat(t))},l=function(t){return n["a"].post("/api/employee",t)},s=function(t){return n["a"].get("/api/employee/owner",{params:t})},i=function(t){return n["a"].post("/api/employee/restore","",{params:{employeeId:t}})},c=function(t,e){return n["a"].post("/api/employee/update",e,{params:{employeeId:t}})},u=function(t){return n["a"].get("/api/employee/".concat(t,"/attachments"))},p=function(t,e,a){return n["a"].get("/api/contract/",{params:{page:e,size:a,employeeId:t}})},d=function(t,e){return n["a"].post("/api/adjustment",e,{params:{employeeId:t}})},f=function(t,e,a){return n["a"].get("/api/adjustment",{params:{page:e,size:a,employeeId:t}})},m=function(t,e){return n["a"].post("/api/adjustment/discharge",e,{params:{employeeId:t}})},y=function(t,e){return n["a"].post("/api/adjustment/".concat(t),e)},v=function(t,e){return e.set("employeeId",t),n["a"].post("/api/employee/password",e)}},f194:function(t,e,a){"use strict";var n=a("e8e3"),o=a.n(n);o.a},fa80:function(t,e,a){"use strict";var n=a("96c9"),o=a.n(n);o.a}}]);
//# sourceMappingURL=chunk-f9b2b916.08735577.js.map