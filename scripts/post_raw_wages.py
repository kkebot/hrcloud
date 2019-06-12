import datetime
import sys
from api_worker import RestApiWorker
from os import listdir
from os.path import isfile, join

year = int(sys.argv[1])
path = f"./data/wages/{year}"
wage_paths = [join(path, f) for f in listdir(path) if isfile(join(path, f))]

operator = RestApiWorker(un="kke", pw="kke")

for fullname in wage_paths:
    with open(fullname, mode='rb') as f:
        files = {"file": f}
        code, *_ = operator.post(path='/api/wage/upload/raw', files=files)
        if code != 200:
            print(f'[{code}] creating wage of', fullname)


