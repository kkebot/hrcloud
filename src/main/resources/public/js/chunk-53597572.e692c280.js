(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-53597572"],{"09b7":function(e,t,n){"use strict";var a=n("d2ef"),i=n.n(a);i.a},"2fdb":function(e,t,n){"use strict";var a=n("5ca1"),i=n("d2c8"),l="includes";a(a.P+a.F*n("5147")(l),"String",{includes:function(e){return!!~i(this,e,l).indexOf(e,arguments.length>1?arguments[1]:void 0)}})},"34c6":function(e,t,n){"use strict";n.d(t,"b",function(){return i}),n.d(t,"a",function(){return l}),n.d(t,"c",function(){return o});var a=n("b775"),i=function(e,t,n){return a["a"].get("/api/rule",{params:{status:e,page:t,size:n}})},l=function(e){return a["a"].post("/api/rule/unregister","",{params:{ruleId:e}})},o=function(e){return a["a"].post("/api/rule/register",e)}},3846:function(e,t,n){n("9e1e")&&"g"!=/./g.flags&&n("86cc").f(RegExp.prototype,"flags",{configurable:!0,get:n("0bfb")})},5147:function(e,t,n){var a=n("2b4c")("match");e.exports=function(e){var t=/./;try{"/./"[e](t)}catch(n){try{return t[a]=!1,!"/./"[e](t)}catch(i){}}return!0}},6534:function(e,t,n){},6762:function(e,t,n){"use strict";var a=n("5ca1"),i=n("c366")(!0);a(a.P,"Array",{includes:function(e){return i(this,e,arguments.length>1?arguments[1]:void 0)}}),n("9c6c")("includes")},"6b02":function(e,t,n){"use strict";n.r(t);var a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",[n("Card",[n("Row",{attrs:{type:"flex",gutter:20}},[n("Col",{attrs:{span:"5"}},[n("Divider",{attrs:{orientation:"left"}},[e._v("选择第一个表")]),n("Select",{on:{"on-change":e.changeFrom}},e._l(e.database,function(t){return n("Option",{key:t.name,attrs:{value:t.name}},[e._v(e._s(t.text))])}),1),n("Divider",{attrs:{orientation:"left"}},[e._v("选择消息主体")]),n("Select",{on:{"on-change":e.changeSelect},model:{value:e.clauses.select,callback:function(t){e.$set(e.clauses,"select",t)},expression:"clauses.select"}},e._l(e.aliases,function(t,a){return n("Option",{key:t.alias+"-"+a,attrs:{value:t.name,label:t.text}},[e._v("\n            "+e._s(t.text)+"("+e._s(t.alias)+")\n          ")])}),1),n("Divider",{attrs:{orientation:"left"}},[e._v("选择连接表")]),n("Select",{attrs:{multiple:"","max-tag-count":3},on:{"on-change":e.changeJointTables},model:{value:e.joint,callback:function(t){e.joint=t},expression:"joint"}},e._l(e.joinable,function(t,a){return n("Option",{key:t.alias+"-"+a,attrs:{value:t.alias,label:t.text}},[e._v("\n            "+e._s(t.text)+"("+e._s(t.alias)+")\n          ")])}),1),n("Divider",{attrs:{orientation:"left"}},[e._v("描述规则")]),n("div",[n("Form",{ref:"form",attrs:{"label-width":40,"label-position":"left"},model:{value:e.formData,callback:function(t){e.formData=t},expression:"formData"}},[n("FormItem",{attrs:{label:"概要",prop:"overview"}},[n("Input",{model:{value:e.formData.overview,callback:function(t){e.$set(e.formData,"overview",t)},expression:"formData.overview"}})],1),n("FormItem",{attrs:{label:"描述",prop:"description"}},[n("Input",{attrs:{type:"textarea",placeholder:"请务必仔细且详尽地描述该规则的条件和参数！"},model:{value:e.formData.description,callback:function(t){e.$set(e.formData,"description",t)},expression:"formData.description"}})],1)],1)],1)],1),n("Col",{attrs:{span:"19"}},[n("Divider",{attrs:{orientation:"left"}},[e._v("配置条件")]),n("Row",{staticClass:"group-style",attrs:{type:"flex",justify:"center",align:"middle",gutter:20}},[n("Col",[n("ButtonGroup",[n("Button",{attrs:{disabled:!!e.clauses.where.length,type:"success"},on:{click:function(t){return e.addCondition()}}},[e._v("添加")]),e._l(e.conjunctions,function(t){return n("Button",{key:t.value,attrs:{disabled:!e.clauses.where.length},on:{click:function(n){return e.addCondition(t)}}},[e._v(e._s(t.text)+"\n              ")])})],2)],1),n("Col",[n("CheckboxGroup",{model:{value:e.useAggregate,callback:function(t){e.useAggregate=t},expression:"useAggregate"}},[n("Checkbox",{attrs:{label:"group"}},[e._v("按列分组")]),n("Checkbox",{attrs:{label:"having",disabled:!e.useAggregate.includes("group")}},[e._v("分组筛选")])],1)],1)],1),e._l(e.clauses.where,function(t,a){return[n("Row",{key:t.key,attrs:{type:"flex",align:"middle",gutter:20}},[t.conj?n("Col",[n("Tag",[e._v(e._s(t.conj.text))])],1):e._e(),n("Col",[n("slots-expr",{attrs:{selectable:e.selectable},model:{value:t.value,callback:function(n){e.$set(t,"value",n)},expression:"clause.value"}})],1),a||1===e.clauses.where.length?n("Col",[n("Button",{attrs:{type:"error",icon:"md-backspace"},on:{click:function(t){return e.clauses.where.splice(a,1)}}})],1):e._e()],1),n("br")]}),e.useAggregate.includes("group")?[n("Row",{attrs:{type:"flex",align:"middle",gutter:10}},[n("Col",[n("Tag",{attrs:{color:"primary"}},[e._v("按列分组")])],1),n("Col",[n("slots-expr",{attrs:{separator:",",selectable:{column:e.selectable.column}},model:{value:e.clauses.groupBy,callback:function(t){e.$set(e.clauses,"groupBy",t)},expression:"clauses.groupBy"}})],1)],1),n("br")]:e._e(),e.useAggregate.includes("having")?[n("Row",{attrs:{type:"flex",align:"middle",gutter:10}},[n("Col",[n("Tag",{attrs:{color:"warning"}},[e._v("筛选分组")])],1),n("Col",[n("slots-expr",{attrs:{selectable:e.selectable},model:{value:e.clauses.having,callback:function(t){e.$set(e.clauses,"having",t)},expression:"clauses.having"}})],1)],1)]:e._e()],2)],1),n("br"),n("Row",{attrs:{type:"flex",gutter:20}},[n("Col",{attrs:{span:"5"}},[n("Button",{attrs:{long:"",type:"success",disabled:!e.formData.description},on:{click:e.setup}},[e._v("设置提醒")])],1)],1)],1)],1)},i=[],l=(n("7514"),n("7f7f"),n("6762"),n("2fdb"),n("ac6a"),n("cebc")),o=n("af6c"),r=(n("6b54"),n("a481"),function(){return"xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g,function(e){var t=16*Math.random()|0,n="x"==e?t:3&t|8;return n.toString(16)})}),s=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("Alert",{staticClass:"alert-padding",attrs:{type:e.isFunction?"info":"warning"}},[n("Row",{attrs:{type:"flex",gutter:10,align:"middle"}},[e.isFunction?[n("Col",[n("Select",{on:{"on-change":e.updateModel},model:{value:e.func,callback:function(t){e.func=t},expression:"func"}},e._l(e.selectable.function,function(t,a){return n("Option",{key:t.value+"-"+a,attrs:{value:t.value}},[e._v(e._s(t.text))])}),1)],1),n("Col",[n("span",{staticClass:"span-text"},[e._v("(")])])]:e._e(),e._l(e.items,function(t,a){return n("Col",{key:t.slot+"-"+a},["operator"===t.slot?[n("Select",{on:{"on-change":e.updateModel},model:{value:t.value,callback:function(n){e.$set(t,"value",n)},expression:"item.value"}},e._l(e.selectable.operator,function(t,a){return n("Option",{key:t.value+"-"+a,attrs:{value:t.value}},[e._v(e._s(t.text))])}),1)]:[n("Row",{attrs:{type:"flex",align:"middle"}},[e.separator&&a>0?n("Col",[n("span",{staticClass:"span-text"},[e._v(e._s(e.separator))])]):e._e(),n("Col",[n("Select",{on:{"on-change":function(n){(t.value="")||e.updateModel()}},model:{value:t.slot,callback:function(n){e.$set(t,"slot",n)},expression:"item.slot"}},e._l(e.slotTypes,function(t,a){return n("Option",{key:t.value+"-"+a,attrs:{value:t.value}},[e._v(e._s(t.text))])}),1)],1),t.slot?n("Col",["text"===t.slot?[n("Input",{staticClass:"value-input",attrs:{clearable:!0},on:{"on-change":e.updateModel},model:{value:t.value,callback:function(n){e.$set(t,"value",n)},expression:"item.value"}})]:"expression"===t.slot?[n("slots-expr",{attrs:{selectable:e.selectable},on:{"change-model":e.updateModel},model:{value:t.value,callback:function(n){e.$set(t,"value",n)},expression:"item.value"}})]:"function"===t.slot?[n("slots-expr",{attrs:{selectable:e.selectable,"is-function":!0,separator:","},on:{"change-model":e.updateModel},model:{value:t.value,callback:function(n){e.$set(t,"value",n)},expression:"item.value"}})]:[n("Select",{on:{"on-change":e.updateModel},model:{value:t.value,callback:function(n){e.$set(t,"value",n)},expression:"item.value"}},e._l(e.selectable[t.slot],function(t,a){return n("Option",{key:t.value+"-"+a,attrs:{value:t.value}},[e._v(e._s(t.text))])}),1)]],2):e._e()],1)]],2)}),e.isFunction?[n("Col",[n("span",{staticClass:"span-text"},[e._v(")")])])]:e._e(),n("Col",[n("ButtonGroup",{attrs:{size:"small"}},[n("Button",{attrs:{icon:"md-add"},on:{click:e.add}}),n("Button",{attrs:{icon:"md-backspace",type:"error",disabled:e.items.length<=1},on:{click:function(t){return e.items.splice(-1,1)}}})],1)],1)],2)],1)},c=[],u={name:"SlotsExpr",model:{event:"change-model"},props:{selectable:{type:Object,default:function(){return{column:[],function:[],operator:[],constant:[]}}},isFunction:{type:Boolean,default:!1},separator:{type:String,default:""}},data:function(){return{func:null,items:[],slotTypes:[{value:"text",text:"输入"},{value:"column",text:"列"},{value:"constant",text:"特殊值"},{value:"expression",text:"表达式"},{value:"function",text:"函数"}]}},mounted:function(){this.add()},computed:{},methods:{updateModel:function(){var e=this.items.map(function(e){return e.value}).join(this.separator),t="".concat(this.func||"","(").concat(e,")");console.log(t),this.$emit("change-model",t)},debugPrint:function(e){console.log(e)},add:function(){var e=this.items;!this.isFunction&&e.length>0&&"operator"!==e[e.length-1].slot?e.push({slot:"operator",value:null}):e.push({slot:null,value:null})}}},d=u,f=(n("09b7"),n("2877")),p=Object(f["a"])(d,s,c,!1,null,"42269f85",null),m=p.exports,v=n("34c6"),x=n("2934"),h={components:{SlotsExpr:m},inject:["reload"],data:function(){return{formData:{description:null,overview:null},useAggregate:[],clauses:{where:[],groupBy:"",having:"",select:"",from:""},database:Object(o["b"])().map(function(e){return Object(l["a"])({},e,{jointTimes:0})}),joint:[],aliases:[],selectable:{function:[{value:"YEAR",text:"年份"},{value:"MONTH",text:"月份"},{value:"DAY",text:"日子"},{value:"CONCAT",text:"拼接"},{value:"MAX",text:"取最大值"},{value:"MIN",text:"取最小值"},{value:"DATEDIFF",text:"日期差"},{value:"COUNT",text:"计数"}],operator:[{value:"+",text:"加"},{value:"-",text:"减"},{value:"*",text:"乘"},{value:"/",text:"除以"},{value:"<",text:"小于"},{value:">",text:"大于"},{value:">=",text:"大于或等于"},{value:"<=",text:"小于或等于"},{value:"!=",text:"不等于"},{value:"=",text:"等于"},{value:"IS",text:"是"},{value:"LIKE",text:"类似"},{value:"NOT",text:"不是"}],constant:[{value:"CURRENT_DATE",text:"今天"},{value:"NULL",text:"空"},{value:"*",text:"默认"}],column:[]},conjunctions:[{value:"AND",text:"并且"},{value:"OR",text:"或者"}]}},computed:{joinable:function(){return this.aliases.slice(1)}},methods:{setup:function(){var e=this,t=this.clauses,n=t.where,a=t.groupBy,i=t.having,o=t.select,r=t.from;if(r){var s=this.getAlias(o),c=this.getAlias(r),u=this.selectable.column,d=["".concat(s.alias,".").concat(s.primary)];u.forEach(function(e){i.includes(e.value)&&d.push(e.value)});var f=this.aliases.filter(function(t){return e.joint.includes(t.alias)}).map(function(e){return"INNER JOIN ".concat(e.name," ").concat(e.alias," ON ").concat(e.joinFrom.alias,".").concat(e.joinFrom.ref,"=").concat(e.alias,".").concat(e.joinTo)}).join("\n"),p=["SELECT DISTINCT "+d.join(","),"FROM ".concat(c.name," ").concat(c.alias),f];if(n.length>0)p.push("WHERE "+n.map(function(e){return"".concat(e.conj&&e.conj.value||""," ").concat(e.value)}).join(" "));else if(!i)return void this.$Notice.error({title:"配置错误",desc:"请配置条件或筛选依据"});a&&p.push("GROUP BY "+a),i&&p.push("HAVING "+i);var m=this.formData,h=p.join("\n");console.log(h),Object(v["c"])(Object(x["b"])(Object(l["a"])({},m,{sqlStatement:h,entityType:o,multiple:d.length>1}))).then(function(t){e.$Notice.success({title:"成功添加规则(".concat(t.data.id,")")}),e.$router.push({name:"NotificationList"})}).catch(function(t){return e.$Notice.error({title:"添加规则失败(".concat(t.status,")"),desc:t.data.message})})}},changeFrom:function(e){this.reset();var t=this.clauses;t.from=e;var n=this.createAlias(e);this.setColumns(this.listAll(n)),this.aliases=[n].concat(this.expand(n)),this.changeSelect(e)},setColumns:function(e){this.selectable.column=e},changeSelect:function(e){var t=this.clauses;t.select=e},getTable:function(e){return this.database.find(function(t){return t.name===e})},getAlias:function(e){return this.aliases.find(function(t){return t.name===e})},reset:function(){this.database.forEach(function(e){return e.jointTimes=0}),this.joint=[],this.aliases=[],this.setColumns([])},createAlias:function(e){var t=this.getTable(e),n=Object(l["a"])({},t,{alias:"_".concat(t.name,"_").concat(t.jointTimes)});return t.jointTimes+=1,n},expand:function(e){var t=this;return e.columns.filter(function(e){return!!e.join}).map(function(n){var a=t.createAlias(n.join.table);return a.text=n.text,a.joinFrom={ref:n.name,alias:e.alias},a.joinTo=n.join.key,a})},listAll:function(e){return e.columns.filter(function(e){return!e.hidden}).map(function(t){return{value:e.alias+"."+t.name,text:"".concat(t.text,"(").concat(e.text,")")}})},changeJointTables:function(){var e=this,t=this.joint,n=this.clauses,a=this.getAlias(n.from),i=this.aliases.filter(function(e){return e.name!==n.from&&(t.includes(e.alias)||e.joinFrom.alias===a.alias)});this.aliases=[].concat.apply(i,t.map(function(t){var n=e.aliases.find(function(e){return e.alias===t});return e.expand(n)})),this.aliases.push(a),this.setColumns([].concat.apply(this.listAll(a),t.map(function(t){var n=e.aliases.find(function(e){return t===e.alias});return e.listAll(n)})))},addCondition:function(e){var t=this.clauses.where;e?t.push({value:null,conj:e,key:r()}):t.push({key:r()})}}},g=h,b=(n("eab3"),Object(f["a"])(g,a,i,!1,null,"633359fe",null));t["default"]=b.exports},"6b54":function(e,t,n){"use strict";n("3846");var a=n("cb7c"),i=n("0bfb"),l=n("9e1e"),o="toString",r=/./[o],s=function(e){n("2aba")(RegExp.prototype,o,e,!0)};n("79e5")(function(){return"/a/b"!=r.call({source:"a",flags:"b"})})?s(function(){var e=a(this);return"/".concat(e.source,"/","flags"in e?e.flags:!l&&e instanceof RegExp?i.call(e):void 0)}):r.name!=o&&s(function(){return r.call(this)})},a481:function(e,t,n){"use strict";var a=n("cb7c"),i=n("4bf8"),l=n("9def"),o=n("4588"),r=n("0390"),s=n("5f1b"),c=Math.max,u=Math.min,d=Math.floor,f=/\$([$&`']|\d\d?|<[^>]*>)/g,p=/\$([$&`']|\d\d?)/g,m=function(e){return void 0===e?e:String(e)};n("214f")("replace",2,function(e,t,n,v){return[function(a,i){var l=e(this),o=void 0==a?void 0:a[t];return void 0!==o?o.call(a,l,i):n.call(String(l),a,i)},function(e,t){var i=v(n,e,this,t);if(i.done)return i.value;var d=a(e),f=String(this),p="function"===typeof t;p||(t=String(t));var h=d.global;if(h){var g=d.unicode;d.lastIndex=0}var b=[];while(1){var _=s(d,f);if(null===_)break;if(b.push(_),!h)break;var y=String(_[0]);""===y&&(d.lastIndex=r(f,l(d.lastIndex),g))}for(var k="",j=0,C=0;C<b.length;C++){_=b[C];for(var w=String(_[0]),A=c(u(o(_.index),f.length),0),S=[],O=1;O<_.length;O++)S.push(m(_[O]));var T=_.groups;if(p){var $=[w].concat(S,A,f);void 0!==T&&$.push(T);var D=String(t.apply(void 0,$))}else D=x(w,f,A,S,T,t);A>=j&&(k+=f.slice(j,A)+D,j=A+w.length)}return k+f.slice(j)}];function x(e,t,a,l,o,r){var s=a+e.length,c=l.length,u=p;return void 0!==o&&(o=i(o),u=f),n.call(r,u,function(n,i){var r;switch(i.charAt(0)){case"$":return"$";case"&":return e;case"`":return t.slice(0,a);case"'":return t.slice(s);case"<":r=o[i.slice(1,-1)];break;default:var u=+i;if(0===u)return n;if(u>c){var f=d(u/10);return 0===f?n:f<=c?void 0===l[f-1]?i.charAt(1):l[f-1]+i.charAt(1):n}r=l[u-1]}return void 0===r?"":r})}})},af6c:function(e,t,n){"use strict";n.d(t,"b",function(){return m}),n.d(t,"a",function(){return v});var a=n("cebc"),i=n("a4bb"),l=n.n(i),o={text:"员工",primary:"id",columns:[{name:"id",hidden:!0},{name:"name",text:"姓名"},{name:"birth",text:"出生日期"},{name:"main_position_id",text:"职务",join:{table:"position",key:"id"}},{name:"administration_id",text:"所属部门",join:{table:"department",key:"id"}},{name:"scale_id",text:"薪资级别",join:{table:"pay_scale",key:"id"}}]},r={text:"岗位",primary:"id",columns:[{name:"id",hidden:!0},{name:"name",text:"名称"},{name:"administration_id",text:"所属部门",join:{table:"department",key:"id"}},{name:"expected_count",text:"编制人数"},{name:"actual_count",text:"当前人数"},{name:"type",text:"岗位类型"},{name:"last_normal",text:"上次满编时间"},{name:"created_at",text:"设立时间"}]},s={text:"薪资级别",primary:"id",columns:[{name:"id",hidden:!0},{name:"grade",text:"级别"},{name:"salary",text:"基础薪资"}]},c={text:"合同",primary:"id",columns:[{name:"id",hidden:!0},{name:"employee_id",text:"所属员工",join:{table:"employee",key:"id"}},{name:"description",text:"描述"},{name:"effective_on",text:"起始时间"},{name:"effective_until",text:"到期时间"}]},u={text:"合同续约",primary:"id",columns:[{name:"original_term",text:"原到期时间"},{name:"renew_term",text:"新到期时间"},{name:"employee_id",text:"所属员工",join:{table:"employee",key:"id"}},{name:"effective_on",text:"生效时间"},{name:"path",text:"审批文件"}]},d={text:"部门",primary:"id",columns:[{name:"id",hidden:!0},{name:"name",text:"名称"},{name:"prefix",text:"前缀"},{name:"created_at",text:"设立日期"},{name:"administration_id",text:"上级",join:{table:"department",key:"id"}}]},f={text:"薪资记录",primary:"id",columns:[{name:"id",hidden:!0},{name:"period",text:"月份"},{name:"employee_id",text:"所有者",join:{table:"employee",key:"id"}},{name:"current_scale_id",text:"当时薪资级别",join:{table:"pay_scale",key:"id"}},{name:"current_position_id",text:"时任职务",join:{table:"position",key:"id"}}]},p={employee:o,position:r,department:d,wage_record:f,pay_scale:s,contract:c,contract_renew:u},m=function(){return l()(p).map(function(e){return Object(a["a"])({},p[e],{name:e})})},v=function(){return p}},d2c8:function(e,t,n){var a=n("aae3"),i=n("be13");e.exports=function(e,t,n){if(a(t))throw TypeError("String#"+n+" doesn't accept regex!");return String(i(e))}},d2ef:function(e,t,n){},eab3:function(e,t,n){"use strict";var a=n("6534"),i=n.n(a);i.a}}]);
//# sourceMappingURL=chunk-53597572.e692c280.js.map