using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class MedicineUsageRepository : RepositoryAsync<MedicineUsage>, IMedicineUsageRepository
    {
        private readonly ApplicationDbContext _db;
        public MedicineUsageRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(MedicineUsage medicineUsage)
        {
        }
    }
}
