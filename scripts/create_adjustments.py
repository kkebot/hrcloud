import datetime
import faker
import sys
from api_worker import RestApiWorker

# from api_worker import Operation


operator = RestApiWorker(un="kke", pw="kke")

fake = faker.Faker('zh-cn')

today = datetime.datetime.today()
*_, employees = operator.get(path='/api/debug/employees')
*_, positions = operator.get(path='/api/debug/positions')
*_, pay_scales= operator.get(path='/api/debug/scales')

if len(sys.argv) > 1:
    chosen = [fake.random.choice(employees) for _ in range(int(sys.argv[1]))]
else:
    chosen = employees
mode = int(sys.argv[2]) % 3 if len(sys.argv) > 2 else 0

for e in chosen:
    p = fake.random.choice(positions)
    s = fake.random.choice(pay_scales)

    data = {
        "description": fake.text(),
        "employeeId": e['id'],
        "effectiveOn": today.replace(microsecond=0).isoformat()+'.000+0800',
    }
    if mode == 2:
        data["toId"] = p['id']
    elif mode == 1:
        data["afterId"] = s['id']
    else:
        data["toId"] = p['id']
        data["afterId"] = s['id']

    code, *_ = operator.post(path='/api/adjustment', data=data)
    if code != 200:
        print(f'[{code}] creating adjustment=', data)



