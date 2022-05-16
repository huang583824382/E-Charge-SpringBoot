# API接口
有问题自己调整

## `/start/login`

|发送|说明|
|--|--|
|wxcode|用于与微信服务器确认的code|

|接收|说明|
|--|--|
|option_code|是否登录成功，或者是否需要注册|
|session_id||
|openid||
|uid||


## `/start/register`

|发送|说明|
|--|--|
|name||
|phone_num|string类型，支持+86等|
|gender||
|image|头像文件|

|接收|说明|
|--|--|
|option_code|是否注册成功|
|uid|返回注册得到的uid|

## `/list/goods`

|发送|说明|
|--|--|
|search|搜索内容，默认为空|

|接收（以下为json数组中一个item的内容）|说明|
|--|--|
|item_id|商品id|
|title||
|price||
|figure_url|封面url|

## `/list/tasks`

|发送|说明|
|--|--|
|search|搜索内容，默认为空|

|接收（以下为json数组中一个item的内容）|说明|
|--|--|
|item_id|商品id|
|title||
|price||
|figure_url|封面url|


## `/commodity/detail`

|发送|说明|
|--|--|
|item_id||

|接收|说明|
|--|--|
|item_id||
|title||
|price||
|figure_urls|url数组，展示用图片|
|description|商品详情|
|state|商品状态，用于显示不同按钮功能|
|release_time|商品发布时间|
|pub_id|发布者id，用于聊天|
|type|商品还是任务|

## `/commodity/publish`

|发送|说明|
|--|--|
|title||
|images|图片文件|
|price||
|discription||
|type||

|接收|说明|
|--|--|
|option_code|是否发布成功，或者在审核中|
|item_id|返回商品id|

## `/commodity/edit`
可暂时不实现
|发送|说明|
|--|--|
|option|更改或删除，数字指令|
|title|更改标题或原标题|
|images|新图片文件|
|delete_image_url|需要删除图片的url
|price|更改价格|
|discription|更改详情|

|接收|说明|
|--|--|
|option_code|操作结果|

## `/trans/buy`

|发送|说明|
|--|--|
|item_id||

|接收|说明|
|--|--|
|option_code|下单成功或失败|
|transaction_id|成功后的订单id|
|item_id|商品id|
|figure_url|商品封面url|
|title|商品title|
|price|价格|
|deal_time|下单时间|
|state|订单状态|
|type|任务或商品|

## `/trans/confirm/buy`
|发送|说明|
|--|--|
|transaction_id||
|price|付款价格|

|接收|说明|
|--|--|
|option_code|是否购买成功|

## `/trans/confirm/finish`

|发送|说明|
|--|--|
|transaciton_id||

|接收|说明|
|--|--|
|option_code|确认收货/任务完成 成功|

## `/trans/list`

|发送|说明|
|--|--|
|state|查询什么类型的订单|

|接收（一个数组item内容)|说明|
|transaction_id||
|deal_time|交易时间|
|type|任务还是商品|
|state|订单状态|
|figure_url|封面图片|

## `/trans/assign` 
|发送|说明|
|--|--|
|user_id|指派对象的is|
|item_id|任务的id|
|price|指派任务的报酬|

|接收|说明|
|--|--|
|option_code|是否指派成功|

## `/user/info` 
|发送|说明|
|--|--|
|uid|如果与请求者相同，代表查询自己信息，可返回credit，如果不同，代表查询其他人信息|

|接收|说明|
|--|--|
|uid|与本地相同，可进行编辑操作|
|name|用户名|
|gender|性别|
|birth||
|credit||
|balance|查询自己信息时返回，否则为none|
|icon_url|头像|
|is_admin|用于显示管理员入口|

## `/user/edit`

|发送|说明|
|--|--|
|name|用户名|
|gender|性别|
|birth||
|icon_url|头像|

|接收|说明|
|--|--|
|option_code|是否修改成功|

## `/report/commodity`

|发送|说明|
|--|--|
|item_id|商品id|
|content|举报内容|
|images|图片文件|

|接收|说明|
|--|--|
|option_code|是否举报成功|

## `/report/user`

|发送|说明|
|--|--|
|uid|举报对象id|
|content|举报内容|
|images|图片文件|

|接收|说明|
|--|--|
|option_code|是否举报成功|

## `/chat/recv`



# 以下为admin相关
## 后端在每次接收到请求时要判断是否有权限，无权限返回 无权限的option_code
## `/admin/list`

|发送|说明|
|--|--|
|type|返回举报类型与审核类型，或者全部返回|

|接收（一个数组item)|说明|
|--|--|
|type|举报还是审核|
|report_id|举报|
|report_time|举报时间|
|item_id|审核的商品id|
|release_time|商品发布时间|


## `/admin/examine`
审核商品
|发送|说明|
|--|--|
|item_id||
|option|操作数，通过与不通过|

|接收|说明|
|--|--|
|option_code|操作结果|

## `/admin/handle`
处理举报
|发送|说明|
|--|--|
|report_id||
|option|操作数，一些操作指令，惩罚用户与下架商品等|
|uid|对用户惩罚措施，用户id|
|credit|+0.5, -0.5等float，操作其信誉积分|
|item_id|商品id|

|接收|说明|
|--|--|
|option_code|操作结果|





























