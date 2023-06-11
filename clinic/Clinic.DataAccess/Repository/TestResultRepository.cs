using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class TestResultRepository : RepositoryAsync<TestResult>, ITestResultRepository
    {
        private readonly ApplicationDbContext _db;
        public TestResultRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(TestResult testResult)
        {
        }
    }
}
