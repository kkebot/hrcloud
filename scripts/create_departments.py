import datetime
import faker
import sys
from api_worker import RestApiWorker

# from api_worker import Operation


operator = RestApiWorker(un="kke", pw="kke")

code, *_, departments = operator.get(path='/api/debug/departments')
fake = faker.Faker('zh-cn')
count = int(sys.argv[1]) if len(sys.argv) > 1 else 30

today = datetime.datetime.today()

for _ in range(count):
    data = {
        "name": fake.city_name(),
        "createdAt": fake.iso8601(end_datetime=today)+'.000+0800',
        "description": fake.sentences(1)[0]
    }
    admin = fake.random.choice(departments)
    code, _, obj = operator.post(path=f"/api/department", data={**data, "departmentId": admin['id']})
    if code != 200:
        print(f'[{code}] creating department=', data, ";under admin=", admin['id'])
    else:
        departments.append(obj)
