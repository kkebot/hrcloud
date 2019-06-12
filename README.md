# HRCloud

HRCloud 的 REST 后台。基于 Spring Boot 开发。

## UI

需要编译前端 HRCloud-UI，然后将静态文件放到 `src/main/resources/public` 下，这样 Spring Boot 就能自动识别 index.html

## Fake Data

可以用 `scripts` 目录下的 python 脚本创建随机数据。

除了 generate_wages.py 和 post_raw_wages.py，其他脚本每次运行脚本都是增量更新：

```bash
# 创建部门
python create_departments.py
# 创建员工
python create_employees.py 
# 创建岗位
python create_positions.py 
# 执行员工变动
python create_adjustments.py 
# 生成 2017 年的薪资记录
python generate_wages.py 2017
# 上传 2017 年的薪资记录
python post_raw_wages.py 2017
```

上述脚本默认后台在本地运行。如果后台部署到其他机器，需要修改位于 api_worker.py 的 host 变量。