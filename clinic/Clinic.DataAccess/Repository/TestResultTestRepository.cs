using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class TestResultTestRepository : RepositoryAsync<TestResultTest>, ITestResultTestRepository
    {
        private readonly ApplicationDbContext _db;
        public TestResultTestRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(TestResultTest testResultTest)
        {
        }
    }
}
