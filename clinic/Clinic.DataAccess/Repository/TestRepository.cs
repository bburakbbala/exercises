using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class TestRepository : RepositoryAsync<Test>, ITestRepository
    {
        private readonly ApplicationDbContext _db;
        public TestRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(Test test)
        {
        }
    }
}
