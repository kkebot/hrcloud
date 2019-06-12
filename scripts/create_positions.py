import datetime
import faker
import sys
from api_worker import RestApiWorker

# from api_worker import Operation


operator = RestApiWorker(un="kke", pw="kke")

*_, departments = operator.get(path='/api/debug/departments')

fake = faker.Faker('zh-cn')
count = int(sys.argv[1]) if len(sys.argv) > 1 else 60

today = datetime.datetime.today()
types = ["行政", "管理", "技术", "后勤"]
for _ in range(count):
    admin = fake.random.choice(departments)
    data = {
        "name": fake.job(),
        "type": fake.random.choice(types),
        "createdAt": fake.iso8601(end_datetime=today)+'.000+0800',
        "expectedCount": fake.random.randint(1,20),
        "description": fake.sentences(1)[0],
    }
    code, _, obj = operator.post(path="/api/position", data={**data, "departmentId": admin['id']})
    if code != 200:
        print(f'[{code}] creating position=', data, ";under admin=", admin['id'])
