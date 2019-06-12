import json

import requests


class RestApiWorker(object):
    host = "http://localhost:8080"
    response_parser = {"json": lambda t: json.loads(t) if t else {}}

    get = None
    post = None

    def __init__(self, un, pw):

        setattr(self, 'get', self._generate('get'))
        setattr(self, 'post', self._generate('post'))

        self.session = requests.session()

        code, *_ = self.post(path="/auth/login", data={"username": un, "password": pw}, response='text')
        assert code == 200

    def _generate(self, method):
        def func(**kwargs):
            kwargs["method"] = method
            return self.execute(**kwargs)

        return func

    def execute(self, **kwargs):
        kwargs.pop("url", None)

        method = getattr(self.session, kwargs.pop("method"))
        response = kwargs.pop("response", "json")
        r = method(self.host + kwargs.pop("path"), **kwargs)
        parser = self.response_parser.get(response, None)
        return r.status_code, r.headers, (r.text if parser is None else parser(r.text))



