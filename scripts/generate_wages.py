import datetime
import os
import random
import sys

import xlwings as xw
from api_worker import RestApiWorker

# from api_worker import Operation


year = int(sys.argv[1])
path = f"./data/wages/{year}"
if not os.path.exists(path):
    os.makedirs(path)

operator = RestApiWorker(un="kke", pw="kke")
*_, employees = operator.get(path='/api/debug/employees')

salary = (2000.0, 10000.0)
bonus = (500.0, 5000.0)
allowance = (100.0, 1000.0)
payRaise = overtimePay = backPay = (50.0, 1000.0)
payCut = (-1000.0, 0.0)

columns = (salary, bonus, allowance, overtimePay, backPay, payRaise, payCut)

app = xw.App(visible=False, add_book=False)
app.display_alerts = False
app.screen_updating = False

wage_paths = []

for i in range(12):
    wb = app.books.add()
    sht = wb.sheets("sheet1")
    period = datetime.datetime(year=year, month=i+1, day=1).replace(microsecond=0).isoformat()+'.000+0800'
    sht.range(f"A1").value = ("编号", "时任职务", "薪资级别", "核算周期", "基本工资", "奖金", "津贴", "加班工资", "欠发工资", "加薪", "减薪")
    for index, e in enumerate(employees):
        pos = e['mainPosition']
        scale = e['scale']
        if None in (pos, scale):
            continue

        row = [e['id'], pos['id'], scale['id'], period] + [round(random.uniform(*p), 2) for p in columns]

        sht.range(f"A{index+2}").value = row
    wage_paths.append(f"{path}/{i+1}.xlsx")
    wb.save(wage_paths[-1])
    wb.close()

app.quit()

print(wage_paths)


