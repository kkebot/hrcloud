(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6f503e32"],{"183c":function(t,e,n){"use strict";var o=n("ce4e"),a=n.n(o);a.a},"19e3":function(t,e,n){"use strict";var o=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("Row",{attrs:{type:"flex",align:"middle",gutter:8}},[t.labelText?n("Col",{attrs:{span:t.labelSpan}},[n("div",{style:!t.labelSpan&&{width:t.labelWidth+"px"}},[n("span",[t._v(t._s(t.labelText))])])]):t._e(),n("Col",[n("Upload",{ref:"upload",attrs:{"show-upload-list":!1,action:"",accept:t.accept,"before-upload":t.handleUpload}},[n("Button",{attrs:{disabled:t.disabled,icon:"md-arrow-up",type:t.color}},[t._v(t._s(t.buttonHint))])],1)],1),t.file?n("Col",[n("Button",{attrs:{size:"small",icon:"md-close"},on:{click:function(e){t.file=null}}})],1):t._e()],1),t.file&&t.showSelected?n("div",[n("br"),n("Alert",[t._v("已选择文件:"+t._s(t.file.name))])],1):t._e()],1)},a=[],r=(n("c5f6"),{name:"UploadButton",props:{labelText:{type:String,default:"上传文件"},labelWidth:{type:Number,default:50},accept:{type:String,default:".txt"},buttonText:{type:String,default:"选择本地文件"},showSelected:{type:Boolean,default:!0},disabled:{type:Boolean,default:!1},labelSpan:{type:Number,default:null},color:{type:String,default:null}},computed:{buttonHint:function(){return this.file?"重新选择":this.buttonText}},data:function(){return{file:null,labelClazz:{width:this.labelWidth}}},methods:{handleUpload:function(t){return this.file=t,this.$emit("on-file-ready",t),!1},reset:function(){this.file=null}}}),i=r,s=n("2877"),c=Object(s["a"])(i,o,a,!1,null,"7487a8df",null);e["a"]=c.exports},"1a4e":function(t,e,n){"use strict";var o=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("Form",{ref:"form",attrs:{"label-width":100,"label-position":"left",model:t.formData}},[n("FormItem",{staticClass:"form-label-font",attrs:{label:"生效时间",prop:"effectiveOn"}},[n("DatePicker",{attrs:{type:"datetime",editable:!1},model:{value:t.formData.effectiveOn,callback:function(e){t.$set(t.formData,"effectiveOn",e)},expression:"formData.effectiveOn"}})],1),n("FormItem",{staticClass:"form-label-font",attrs:{label:"描述文字",prop:"description"}},[n("Input",{model:{value:t.formData.description,callback:function(e){t.$set(t.formData,"description",e)},expression:"formData.description"}})],1)],1),n("upload-button",{ref:"upload",attrs:{accept:".doc,.docx","label-width":100},on:{"on-file-ready":function(e){t.formData.file=e}}}),t.useButton?n("div",[n("br"),n("Button",{attrs:{type:"success"},on:{click:t.onOk}},[t._v("确定")])],1):t._e()],1)},a=[],r=(n("6b54"),n("19e3")),i=n("2934"),s={name:"SimpleChangeForm",components:{UploadButton:r["a"]},props:{useButton:{type:Boolean,default:!1},effectiveOn:{type:String,default:function(){return(new Date).toString()}},description:{type:String,default:""}},data:function(){return{formData:this.defaultForm()}},methods:{defaultForm:function(){return{effectiveOn:new Date(this.effectiveOn),description:this.description,file:null}},reset:function(){this.formData=this.defaultForm(),this.$refs.upload.reset()},getFormData:function(){var t=this.formData;return Object(i["b"])(t)},onOk:function(){this.$emit("on-form-ready",this.getFormData()),this.reset()},debugPrint:function(){var t=this.effectiveOn,e=this.formData;console.log({effectiveOn:t,formData:e})}}},c=s,l=(n("fa80"),n("2877")),u=Object(l["a"])(c,o,a,!1,null,"952f6d5e",null);e["a"]=u.exports},2105:function(t,e,n){},"2db4":function(t,e,n){"use strict";n.d(e,"a",function(){return o});var o=function(t,e){return!t||t.length<=e?t||"":t.slice(0,e)+"..."}},"2fdb":function(t,e,n){"use strict";var o=n("5ca1"),a=n("d2c8"),r="includes";o(o.P+o.F*n("5147")(r),"String",{includes:function(t){return!!~a(this,t,r).indexOf(t,arguments.length>1?arguments[1]:void 0)}})},3846:function(t,e,n){n("9e1e")&&"g"!=/./g.flags&&n("86cc").f(RegExp.prototype,"flags",{configurable:!0,get:n("0bfb")})},5147:function(t,e,n){var o=n("2b4c")("match");t.exports=function(t){var e=/./;try{"/./"[t](e)}catch(n){try{return e[o]=!1,!"/./"[t](e)}catch(a){}}return!0}},6762:function(t,e,n){"use strict";var o=n("5ca1"),a=n("c366")(!0);o(o.P,"Array",{includes:function(t){return a(this,t,arguments.length>1?arguments[1]:void 0)}}),n("9c6c")("includes")},"6b54":function(t,e,n){"use strict";n("3846");var o=n("cb7c"),a=n("0bfb"),r=n("9e1e"),i="toString",s=/./[i],c=function(t){n("2aba")(RegExp.prototype,i,t,!0)};n("79e5")(function(){return"/a/b"!=s.call({source:"a",flags:"b"})})?c(function(){var t=o(this);return"/".concat(t.source,"/","flags"in t?t.flags:!r&&t instanceof RegExp?a.call(t):void 0)}):s.name!=i&&c(function(){return s.call(this)})},"6e0e":function(t,e,n){"use strict";n.r(e);var o=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("Details",{key:t.$route.params.id})},a=[],r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return t.position?n("div",[n("Row",{attrs:{type:"flex",gutter:20}},[n("Col",{attrs:{span:"8"}},[n("Card",[n("Divider",{attrs:{orientation:"left"}},[t._v("岗位信息")]),n("position-view",{attrs:{position:t.position}}),n("Divider"),t.position&&t.isAdmin?n("Row",{attrs:{type:"flex",justify:"space-between"}},[n("Col",[n("Button",{attrs:{type:"primary",to:{name:"PositionUpdate",query:{positionId:t.position.id}}}},[t._v("编辑岗位")])],1),n("Col",[n("Button",{attrs:{disabled:!t.position.status,type:"error"},on:{click:function(e){t.modal=!0}}},[t._v("撤销")])],1)],1):t._e()],1)],1),n("Col",{attrs:{span:"16"}},[n("Card",[n("Divider",{attrs:{orientation:"left"}},[t._v("所属员工")]),n("employee-table",{attrs:{"load-data":t.loadEmployees,simple:""}})],1)],1)],1),n("br"),t.isAdmin?n("Card",[n("Divider",{attrs:{orientation:"left"}},[t._v(t._s(t.position.name)+" - 职务变动")]),n("adjustment-table",{attrs:{"load-data":t.loadAdjustments}})],1):t._e(),n("Modal",{attrs:{title:"撤销岗位","footer-hide":!0},model:{value:t.modal,callback:function(e){t.modal=e},expression:"modal"}},[n("Alert",{attrs:{type:"warning"}},[t._v("\n      确认要撤销岗位？\n      "),n("template",{slot:"desc"},[t._v("此操作不可恢复")])],2),n("Divider"),n("Button",{attrs:{type:"error",long:""},on:{click:t.cancelPosition}},[t._v("撤销")])],1)],1):t._e()},i=[],s=(n("a481"),function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("Row",{attrs:{type:"flex"}},[n("Col",{attrs:{span:"6"}},[n("label",[t._v("名称")])]),n("Col",[t._v(t._s(t.position.name))])],1),n("br"),n("Row",{attrs:{type:"flex"}},[n("Col",{attrs:{span:"6"}},[n("label",[t._v("编号")])]),n("Col",[t._v(t._s(t.position.id))])],1),n("br"),n("Row",{attrs:{type:"flex",gutter:10,align:"middle"}},[n("Col",{attrs:{span:"6"}},[n("label",[t._v("所属部门")])]),n("Col",[n("Tag",[t._v(t._s(t._f("formatEntity")(t.position.administration)))])],1),n("Col",[n("ButtonGroup",{attrs:{size:"small"}},[n("Button",{attrs:{icon:"md-git-branch",to:{name:"Management",query:{departmentId:t.position.administration.id}}}}),n("Button",{attrs:{type:"primary",icon:"md-arrow-forward",to:{name:"DepartmentDetails",params:{id:t.position.administration.id}}}})],1)],1)],1),n("br"),n("Row",{attrs:{type:"flex",align:"middle"}},[n("Col",{attrs:{span:"6"}},[n("label",[t._v("状态")])]),n("Col",[n("Tag",{attrs:{color:t.statusColor}},[t._v(t._s(t.statusText))])],1)],1),n("br"),n("Row",{attrs:{type:"flex"}},[n("Col",{attrs:{span:"6"}},[n("label",[t._v("设立时间")])]),n("Col",[t._v(t._s(t._f("formatDate")(t.position.createdAt)))])],1),n("br"),n("Row",{attrs:{type:"flex"}},[n("Col",{attrs:{span:"6"}},[n("label",[t._v("岗位类型")])]),n("Col",[t._v(t._s(t.position.type))])],1),n("br"),n("Row",{attrs:{type:"flex"}},[n("Col",{attrs:{span:"6"}},[n("label",[t._v("描述")])]),n("Col",[t._v(t._s(t.position.description))])],1),n("br"),n("Row",{attrs:{type:"flex",align:"middle"}},[n("Col",{attrs:{span:"6"}},[n("label",[t._v("编制人数")])]),n("Col",[n("Tag",[t._v(t._s(t.position.expectedCount))])],1)],1),n("br"),n("Row",{attrs:{type:"flex",align:"middle"}},[n("Col",{attrs:{span:"6"}},[n("label",[t._v("当前人数")])]),n("Col",[n("Tag",{attrs:{color:t.actualCountColor}},[t._v("\n        "+t._s(t.position.actualCount)+"\n      ")])],1)],1),n("br"),n("Row",{attrs:{type:"flex"}},[n("Col",{attrs:{span:"6"}},[n("label",[t._v("上次满编")])]),n("Col",[t._v(t._s(t._f("formatDate")(t.position.lastNormal)))])],1),n("br"),n("Row",{attrs:{type:"flex",align:"middle"}},[n("Col",{attrs:{span:"6"}},[n("label",[t._v("审批文件")])]),n("Col",[n("Button",{attrs:{size:"small",type:"primary",disabled:!t.position.resource},on:{click:t.download}},[t._v("下载")])],1)],1)],1)}),c=[],l=n("6c6e"),u=n("f867"),d=n("8454"),p={name:"PositionView",components:{PageData:u["a"]},props:["position"],computed:{statusText:function(){return this.position.status?"启用":"已撤销"},statusColor:function(){return this.position.status?"success":"error"},actualCountColor:function(){var t=this.position;return t.actualCount>t.expectedCount?"error":t.actualCount<t.expectedCount?"warning":"success"}},filters:{formatDate:d["b"],formatEntity:d["e"]},methods:{download:function(){Object(l["a"])(this.position.resource.id)}}},f=p,m=n("2877"),b=Object(m["a"])(f,s,c,!1,null,"43a2bb96",null),h=b.exports,v=n("ef75"),y=n("945c"),g=n("fdf9"),_=n("6e6f"),x=n("ed08"),w={components:{AdjustmentTable:g["a"],PageData:u["a"],EmployeeTable:y["a"],PositionView:h},name:"Details",inject:["reload"],data:function(){return{modal:!1,position:null,isAdmin:Object(x["c"])(["ADMIN"])}},mounted:function(){this.refreshData(this.$route.params.id)},methods:{loadEmployees:function(t,e,n){var o=this,a=this.position.id;Object(v["i"])("position",a,t,e).then(function(t){var e=t.data,o=e.content,a=e.totalElements;n(o,a)}).catch(function(t){return o.$Notice.error({title:"获取员工列表失败(".concat(t.status,")"),desc:t.data.message})})},loadAdjustments:function(t,e,n){var o=this,a=this.position.id;Object(_["b"])(a,t,e).then(function(t){var e=t.data,o=e.content,a=e.totalElements;n(o,a)}).catch(function(t){return o.$Notice.error({title:"获取职务变动失败(".concat(t.status,")"),desc:t.data.message})})},cancelPosition:function(){var t=this,e=this.position.id;Object(_["a"])(e).then(function(e){t.$Notice.success({title:"撤销岗位成功"}),t.reload()}).catch(function(e){var n="无法删除",o=e.data.message;t.$Notice.error({title:n,desc:o})}),this.modal=!1},refreshData:function(t){var e=this;Object(_["d"])(t).then(function(t){return e.position=t.data}).catch(function(t){return e.$Notice.error({title:"获取岗位信息失败".concat(t.status),desc:t.data.message})}),this.$router.replace({name:"PositionDetails",params:{id:t}})}}},k=w,O=(n("183c"),Object(m["a"])(k,r,i,!1,null,"28c39542",null)),D=O.exports,C={components:{Details:D}},j=C,$=Object(m["a"])(j,o,a,!1,null,null,null);e["default"]=$.exports},"6e6f":function(t,e,n){"use strict";n.d(e,"e",function(){return a}),n.d(e,"c",function(){return r}),n.d(e,"d",function(){return i}),n.d(e,"b",function(){return s}),n.d(e,"a",function(){return c}),n.d(e,"f",function(){return l});var o=n("b775"),a=function(t,e){return o["a"].post("/api/position",e,{params:{departmentId:t}})},r=function(t){return o["a"].get("/api/position/".concat(t))},i=function(t){return o["a"].get("/api/position/".concat(t,"/details"))},s=function(t,e,n){return o["a"].get("/api/position/".concat(t,"/adjustments"),{params:{page:e,size:n}})},c=function(t){return o["a"].post("/api/position/cancel","",{params:{positionId:t}})},l=function(t,e){return o["a"].post("/api/position/update",e,{params:{positionId:t}})}},"945c":function(t,e,n){"use strict";var o=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("page-data",{ref:"table",attrs:{columns:t.columns,"load-data":t.loadData}},[t._t("default")],2)},a=[],r=(n("6762"),n("f867")),i=n("2db4"),s=n("8454"),c={name:"EmployeeTable",components:{PageData:r["a"]},props:{simple:{type:Boolean,default:!1},loadData:{type:Function,default:function(){return function(t,e,n){n([],0)}}},useOperations:{type:Boolean,default:!0}},data:function(){var t=this,e=[{title:"工号",key:"id"},{title:"姓名",key:"name"},{title:"性别",key:"gender"},{title:"证件号码",key:"credential",render:function(t,e){var n=e.row;return t("span",Object(i["a"])(n.credential,8))}},{title:"出生年月",key:"birth",render:function(t,e){var n=e.row;return t("span",Object(s["b"])(n.birth))},exportData:function(t){return Object(s["b"])(t.birth)}},{title:"入职时间",key:"entryTime",render:function(t,e){var n=e.row;return t("span",Object(s["b"])(n.entryTime))},exportData:function(t){return Object(s["b"])(t.entryTime)}},{title:"职务",key:"mainPosition",render:function(e,n){var o=n.row,a=o.mainPosition;return a?e("Button",{props:{size:"small",type:"primary"},on:{click:function(){return t.$router.push({name:"PositionDetails",params:{id:a.id}})}}},Object(i["a"])(Object(s["e"])(o.mainPosition),5)):e("span","/")},exportData:function(t){return Object(s["e"])(t.mainPosition)}},{title:"薪资级别",key:"scale",render:function(t,e){var n=e.row;return t("Tag",Object(s["h"])(n.scale))},exportData:function(t){return Object(s["h"])(t.scale)}},{title:"状态",key:"status",render:function(t,e){var n=e.row;return t("Tag",{props:{color:n.status?"success":"error"}},Object(s["d"])(n.status))},exportData:function(t){return Object(s["d"])(t.status)}},{title:"联系方式",key:"phoneNumbers",render:function(t,e){var n=e.row;return t("span",n.phoneNumbers[0])},exportData:function(t){return t.phoneNumbers.join("/")}},{title:"住址",key:"address",render:function(t,e){var n=e.row;return t("span",Object(i["a"])(n.address,8))}},{title:"操作",key:"operations",render:function(e,n){var o=n.row;return e("Button",{props:{size:"small",icon:"md-person"},on:{click:function(){return t.$router.push({name:"EmployeePanel",params:{id:o.id}})}}})},noExport:!0}],n=["id","name","gender","status","mainPosition","scale","operations"],o=this.simple?e.filter(function(t){return n.includes(t.key)}):e;return{columns:this.useOperations?o:o.slice(0,-1)}},methods:{resetPage:function(){this.$refs.table.resetPage()}}},l=c,u=n("2877"),d=Object(u["a"])(l,o,a,!1,null,"017e05ac",null);e["a"]=d.exports},"96c9":function(t,e,n){},a481:function(t,e,n){"use strict";var o=n("cb7c"),a=n("4bf8"),r=n("9def"),i=n("4588"),s=n("0390"),c=n("5f1b"),l=Math.max,u=Math.min,d=Math.floor,p=/\$([$&`']|\d\d?|<[^>]*>)/g,f=/\$([$&`']|\d\d?)/g,m=function(t){return void 0===t?t:String(t)};n("214f")("replace",2,function(t,e,n,b){return[function(o,a){var r=t(this),i=void 0==o?void 0:o[e];return void 0!==i?i.call(o,r,a):n.call(String(r),o,a)},function(t,e){var a=b(n,t,this,e);if(a.done)return a.value;var d=o(t),p=String(this),f="function"===typeof e;f||(e=String(e));var v=d.global;if(v){var y=d.unicode;d.lastIndex=0}var g=[];while(1){var _=c(d,p);if(null===_)break;if(g.push(_),!v)break;var x=String(_[0]);""===x&&(d.lastIndex=s(p,r(d.lastIndex),y))}for(var w="",k=0,O=0;O<g.length;O++){_=g[O];for(var D=String(_[0]),C=l(u(i(_.index),p.length),0),j=[],$=1;$<_.length;$++)j.push(m(_[$]));var P=_.groups;if(f){var E=[D].concat(j,C,p);void 0!==P&&E.push(P);var B=String(e.apply(void 0,E))}else B=h(D,p,C,j,P,e);C>=k&&(w+=p.slice(k,C)+B,k=C+D.length)}return w+p.slice(k)}];function h(t,e,o,r,i,s){var c=o+t.length,l=r.length,u=f;return void 0!==i&&(i=a(i),u=p),n.call(s,u,function(n,a){var s;switch(a.charAt(0)){case"$":return"$";case"&":return t;case"`":return e.slice(0,o);case"'":return e.slice(c);case"<":s=i[a.slice(1,-1)];break;default:var u=+a;if(0===u)return n;if(u>l){var p=d(u/10);return 0===p?n:p<=l?void 0===r[p-1]?a.charAt(1):r[p-1]+a.charAt(1):n}s=r[u-1]}return void 0===s?"":s})}})},ce4e:function(t,e,n){},d2c8:function(t,e,n){var o=n("aae3"),a=n("be13");t.exports=function(t,e,n){if(o(e))throw TypeError("String#"+n+" doesn't accept regex!");return String(a(t))}},dd99:function(t,e,n){"use strict";var o=n("2105"),a=n.n(o);a.a},ef75:function(t,e,n){"use strict";n.d(e,"i",function(){return a}),n.d(e,"g",function(){return r}),n.d(e,"h",function(){return i}),n.d(e,"j",function(){return s}),n.d(e,"k",function(){return c}),n.d(e,"m",function(){return l}),n.d(e,"e",function(){return u}),n.d(e,"f",function(){return d}),n.d(e,"a",function(){return p}),n.d(e,"d",function(){return f}),n.d(e,"c",function(){return m}),n.d(e,"l",function(){return b}),n.d(e,"b",function(){return h});var o=n("b775"),a=function(t,e,n,a){return o["a"].get("/api/employee/query",{params:{entityType:t,entityId:e,page:n,size:a}})},r=function(t){return o["a"].get("/api/employee/".concat(t))},i=function(t){return o["a"].post("/api/employee",t)},s=function(t){return o["a"].get("/api/employee/owner",{params:t})},c=function(t){return o["a"].post("/api/employee/restore","",{params:{employeeId:t}})},l=function(t,e){return o["a"].post("/api/employee/update",e,{params:{employeeId:t}})},u=function(t){return o["a"].get("/api/employee/".concat(t,"/attachments"))},d=function(t,e,n){return o["a"].get("/api/contract/",{params:{page:e,size:n,employeeId:t}})},p=function(t,e){return o["a"].post("/api/adjustment",e,{params:{employeeId:t}})},f=function(t,e,n){return o["a"].get("/api/adjustment",{params:{page:e,size:n,employeeId:t}})},m=function(t,e){return o["a"].post("/api/adjustment/discharge",e,{params:{employeeId:t}})},b=function(t,e){return o["a"].post("/api/adjustment/".concat(t),e)},h=function(t,e){return e.set("employeeId",t),o["a"].post("/api/employee/password",e)}},f867:function(t,e,n){"use strict";var o=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("Row",{attrs:{type:"flex",align:"middle",justify:"space-between"}},[n("Col",{attrs:{span:"12"}},[n("Row",{attrs:{type:"flex",gutter:5}},[n("Col",[t.useExport?n("Button",{attrs:{type:"success"},on:{click:t.onExport}},[t._v("导出CSV")]):t._e()],1),n("Col",[n("Select",{attrs:{multiple:"","max-tag-count":3,placeholder:"选择显示列"},on:{"on-change":t.showColumns},model:{value:t.keys,callback:function(e){t.keys=e},expression:"keys"}},t._l(t.columns,function(e){return n("Option",{key:e.key,attrs:{value:e.key}},[t._v(t._s(e.title))])}),1)],1)],1)],1),n("Col",[t._t("default")],2)],1),n("br"),n("Table",{ref:"table",staticClass:"query-list__table",attrs:{columns:t.displayed,data:t.content},on:{"on-row-dblclick":function(e){return t.$emit("on-db-click",e)}}}),n("br"),n("Row",{attrs:{type:"flex",justify:"end"}},[n("Col",[n("Page",{attrs:{current:t.page,total:t.totalElements,"page-size":t.size,"show-sizer":"","show-total":""},on:{"on-change":function(e){return t.onPage(e,t.size)},"on-page-size-change":function(e){return t.onPage(t.page,e)}}})],1)],1)],1)},a=[],r=(n("6762"),n("2fdb"),n("ac6a"),{name:"PageData",props:{columns:{type:Array,default:function(){return[]}},loadData:{type:Function,default:function(){return function(t,e,n){n([],0)}}},useExport:{type:Boolean,default:!0}},data:function(){return{keys:this.columns.map(function(t){return t.key}),displayed:this.columns.slice(),page:1,size:10,totalElements:1,content:[]}},mounted:function(){this.resetPage()},methods:{onExport:function(){var t=this;this.$refs.table.exportCsv({filename:"temp.csv",original:!1,columns:this.displayed,data:this.content.map(function(e){var n={};return t.displayed.forEach(function(t){t.noExport||(t.exportData?n[t.key]=t.exportData(e):n[t.key]=e[t.key])}),n})})},showColumns:function(){var t=this;this.displayed=this.columns.filter(function(e){return t.keys.includes(e.key)})},resetPage:function(){this.onPage(1,10)},onPage:function(t,e){this.page=t,this.size=e,this.fetchPage()},fetchPage:function(){var t=this,e=this.page,n=this.size;this.loadData(e-1,n,function(e,n){t.totalElements=n,t.content=e})},getData:function(){return this.content}}}),i=r,s=(n("dd99"),n("2877")),c=Object(s["a"])(i,o,a,!1,null,"8d711b90",null);e["a"]=c.exports},fa80:function(t,e,n){"use strict";var o=n("96c9"),a=n.n(o);a.a},fdf9:function(t,e,n){"use strict";var o=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("page-data",{attrs:{columns:t.columns,"load-data":t.loadData}})},a=[],r=n("f867"),i=n("2db4"),s=n("8454"),c=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("Row",{attrs:{type:"flex",gutter:5}},[n("Col",[n("Button",{attrs:{size:"small",shape:"circle",icon:"md-create"},on:{click:function(e){t.upModal=!0}}})],1),t.record.resource?n("Col",[n("Button",{attrs:{size:"small",shape:"circle",type:"success",icon:"md-download"},on:{click:t.onDownload}})],1):t._e()],1),n("Modal",{attrs:{title:"更新记录","footer-hide":!0},model:{value:t.upModal,callback:function(e){t.upModal=e},expression:"upModal"}},[n("simple-change-form",{attrs:{"use-button":!0,"effective-on":t.record.effectiveOn,description:t.record.description},on:{"on-form-ready":t.onOk}})],1)],1)},l=[],u=n("1a4e"),d=n("6c6e"),p={name:"SimpleChangeOps",components:{SimpleChangeForm:u["a"]},props:{record:{type:Object,default:function(){return{effectiveOn:new Date,description:""}}}},inject:["reload"],data:function(){return{upModal:!1}},methods:{onDownload:function(){Object(d["a"])(this.record.resource.id)},onOk:function(t){this.$emit("on-form-ready",t),this.upModal=!1}}},f=p,m=n("2877"),b=Object(m["a"])(f,c,l,!1,null,"dbda6c26",null),h=b.exports,v=n("ef75"),y={name:"AdjustmentTable",components:{PageData:r["a"]},inject:["reload"],props:{loadData:{type:Function,default:function(){return function(t,e,n){n([],0)}}},useOperations:{type:Boolean,default:!0}},data:function(){var t=this,e=[{title:"序号",key:"id"},{title:"员工",key:"employee",render:function(e,n){var o=n.row;return e("Button",{props:{size:"small"},on:{click:function(){return t.$router.push({name:"EmployeePanel",params:{id:o.employee.id},query:{tab:"adjustment"}})}}},Object(i["a"])(Object(s["e"])(o.employee),5))},exportData:function(t){return Object(s["e"])(t.employee)}},{title:"职务变动",key:"to",render:function(e,n){var o=n.row;return t.renderPosition(e,o.to)},exportData:function(t){return Object(s["e"])(t.to)}},{title:"调整后",key:"after",render:function(t,e){var n=e.row;return t("Tag",Object(s["h"])(n.after))},exportData:function(t){return Object(s["h"])(t.after)}},{title:"描述",key:"description",render:function(t,e){var n=e.row;return t("span",Object(i["a"])(n.description,25))},exportData:function(t){return'"'+t.description+'"'}},{title:"生效日期",key:"effectiveOn",render:function(t,e){var n=e.row;return t("span",Object(s["b"])(n.effectiveOn))},exportData:function(t){return Object(s["b"])(t.effectiveOn)}},{title:"查看",key:"check",render:function(t,e){var n=e.row;return t("Button",{props:{size:"small",icon:"md-download",disabled:!n.resource},on:{click:function(){return downloadResourceApi(n.resource.id)}}})},noExport:!0},{title:"操作",key:"operation",render:function(e,n){var o=n.row;return e(h,{props:{record:o},on:{"on-form-ready":function(e){return Object(v["l"])(o.id,e).then(function(e){t.$Notice.success({title:"更新调整历史成功"}),t.reload()}).catch(function(e){return t.$Notice.error({title:"更新调整历史失败",desc:e.data.message})})}}})},noExport:!0}];return{columns:t.useOperations?e:e.slice(0,-1)}},methods:{renderPosition:function(t,e){var n=this;return e?t("Button",{props:{size:"small",type:"primary"},on:{click:function(){return n.$router.push({name:"PositionDetails",params:{id:e.id}})}}},Object(i["a"])(Object(s["e"])(e),5)):t("span","/")}}},g=y,_=Object(m["a"])(g,o,a,!1,null,"bd25f9bc",null);e["a"]=_.exports}}]);
//# sourceMappingURL=chunk-6f503e32.a93f2d37.js.map