const apm = require('elastic-apm-node').start({
  serviceName: 'NodeJS app',
  serverUrl: 'http://localhost:8200',
  token: 'xxVpmQB2HMzCL9PgBHVrnxjNXXw5J7bd79DFm6sjBJR5HPXDhcF8MSb3vv4bpg44',
  distributedTracingOrigins: ['http://localhost:8080']
})

const port = 3001;

const app = require('express')()

app.get('/', function (req, res) {
  console.info('======================================');
  var from = req.query["from"] || 'N/A';
  var t = apm.currentTransaction;  
  console.info('Called / with '  + from + ".")
  console.log(JSON.stringify(req.headers));
  console.info("transaction id=" + t.id + ", traceId = " + t.traceId);
  console.info(t);
  res.json({message:'Hello World! ' + from});  
})

app.get('/a', function (req, res) {
	var err = new Error('Ups, something broke!')
	apm.captureError(err);
});

app.listen(port, () => console.log(`App listening on port ${port}!`))
