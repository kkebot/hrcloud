import datetime
import faker
import sys
from api_worker import RestApiWorker

# from api_worker import Operation


operator = RestApiWorker(un="kke", pw="kke")

fake = faker.Faker('zh-cn')
count = int(sys.argv[1]) if len(sys.argv) > 1 else 100

today = datetime.datetime.today()
marriage = ['未婚','已婚','离异','丧偶']

for _ in range(count):
    ssn = fake.ssn()
    is_male = (int(ssn[-2]) % 2 == 1)
    phone_count = fake.random.randint(1,10)
    data = {
        "name": fake.name_male() if is_male else fake.name_female(),
        "gender": "男" if is_male else "女",
        "credential": ssn,
        "birth": datetime.datetime.strptime(ssn[-12:-4], "%Y%m%d").replace(microsecond=0).isoformat()+'.000+0800',

        "entryTime": fake.iso8601(end_datetime=today)+'.000+0000',

        "address": fake.address(),
        "email": fake.email(),
        "phoneNumbers": [fake.phone_number() for _ in range(phone_count)],

        "college": fake.country(),
        "major": fake.street_name(),
        "graduateTime": fake.iso8601(end_datetime=today)+'.000+0000',

        "marriage": fake.random.choice(marriage),
    }

    code, _, obj = operator.post(path="/api/employee", data=data)
    if code != 200:
        print(f'[{code}] creating employee=', data)
