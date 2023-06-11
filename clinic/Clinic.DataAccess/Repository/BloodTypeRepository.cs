using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class BloodTypeRepository : RepositoryAsync<BloodType>, IBloodTypeRepository
    {
        private readonly ApplicationDbContext _db;
        public BloodTypeRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(BloodType bloodType)
        {
        }
    }
}
