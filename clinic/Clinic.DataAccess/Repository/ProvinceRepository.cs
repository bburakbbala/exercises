using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class ProvinceRepository : RepositoryAsync<Province>, IProvinceRepository
    {
        private readonly ApplicationDbContext _db;
        public ProvinceRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(Province province)
        {
        }
    }
}
