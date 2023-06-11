using Clinic.DataAccess.Data;
using Clinic.DataAccess.Repository.IRepository;
using Clinic.Models;

namespace Clinic.DataAccess.Repository
{
    public class MedicineCategoryRepository : RepositoryAsync<MedicineCategory>, IMedicineCategoryRepository
    {
        private readonly ApplicationDbContext _db;
        public MedicineCategoryRepository(ApplicationDbContext db) : base(db)
        {
            _db = db;
        }

        public void Update(MedicineCategory medicineCategory)
        {
        }
    }
}
